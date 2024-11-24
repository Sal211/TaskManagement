package com.example.TaskManagement.services.imp.otherService;

import com.example.TaskManagement.dtos.OrderDto;
import com.example.TaskManagement.dtos.projections.RevenueProjection;
import com.example.TaskManagement.dtos.projections.SellProjection;
import com.example.TaskManagement.entities.Order;
import com.example.TaskManagement.entities.OrderDetail;
import com.example.TaskManagement.entities.Payment;
import com.example.TaskManagement.exceptions.BusinessException;
import com.example.TaskManagement.repositories.*;
import com.example.TaskManagement.utils.BaseRespone;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRespository customerRespository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public BaseRespone<Void> orderProduct(OrderDto orderDto) {
        BaseRespone<Void> response = new BaseRespone<>();
        try {
            BigDecimal discountMultiplier = BigDecimal.ONE.subtract(
                    BigDecimal.valueOf(orderDto.getDiscount())
                            .divide(BigDecimal.valueOf(100), MathContext.DECIMAL128)
            );

            BigDecimal totalPrice = orderDto.getUnitPrice()
                    .multiply(BigDecimal.valueOf(orderDto.getQuantity()))
                    .multiply(discountMultiplier);

            // FIND CUSTOMER
            var customer = customerRespository.findById(orderDto.getCustomerId());
            if (!customer.isPresent()) {
                throw new Exception("Customer Not Found");
            }

            // FIND PAYMENT METHOD
            var paymentMethod = paymentMethodRepository.findById(orderDto.getPaymentMethodId());
            if (!paymentMethod.isPresent()) {
                throw new Exception("Invalid Payment");
            }

            // CHECK BALANCE USER
            if (orderDto.getPaymentMethodId() == 6) {
                if (customer.get().getBalance().compareTo(totalPrice) < 0) {
                    throw new Exception("Insufficient Balance");
                }
                BigDecimal balance = customer.get().getBalance().subtract(totalPrice);
                customer.get().setBalance(balance);
                customerRespository.save(customer.get());
            }

            // FIND PRODUCT && UPDATE STOCK
            var product = productRepository.findById(orderDto.getProductId());
            if (!product.isPresent()) {
                throw new Exception("Product Not Found");
            }
            if (product.get().getStockQuantity() < orderDto.getQuantity()) {
                throw new Exception("Product Out of Stock");
            }

            // SAVE PRODUCT
            int quantity = product.get().getStockQuantity() - orderDto.getQuantity();
            product.get().setStockQuantity(quantity);
            productRepository.save(product.get());

            // SAVE PAYMENT
            Payment payment = new Payment();
            payment.setAmount(totalPrice);
            payment.setPaymentStatus("Pending");
            payment.setPaymentMethod(paymentMethod.get());
            paymentRepository.save(payment);

            // SAVE ORDER
            Order order = new Order();
            order.setCustomer(customer.get());
            order.setShippingDate(null);
            order.setOrderStatus("Pending");
            orderRepository.save(order);

            // SAVE ORDER DETAIL
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(orderDto, orderDetail);
            orderDetail.setProduct(product.get());
            orderDetail.setPayment(payment);
            orderDetail.setOrder(order);
            orderDetail.setTotalPrice(totalPrice);
            orderDetailRepository.save(orderDetail);

            // RESPONE
            response.setStatus("success");
            return response;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public BaseRespone<Void> cancelOrder(OrderDto orderDto) {
        BaseRespone<Void> response = new BaseRespone<>();
        try {
            // FIND CUSTOMER
            var customer = customerRespository.findById(orderDto.getCustomerId())
                    .orElseThrow(() -> new Exception("Customer Not Found"));

            // SAVE PAYMENT
            var payment = paymentRepository.findById(orderDto.getPaymentId())
                    .orElseThrow(() -> new Exception("Payment Not Found"));
            payment.setPaymentStatus("Cancel"); // Ensure valid status
            paymentRepository.save(payment);

            // CHECK BALANCE USER
            if (payment.getPaymentMethod().getPaymentMethodID() == 6) {
                BigDecimal balance = customer.getBalance().add(orderDto.getTotalPriceOrder());
                customer.setBalance(balance);
                customerRespository.save(customer);
            }

            // FIND PRODUCT && UPDATE STOCK
            var product = productRepository.findById(orderDto.getProductId())
                    .orElseThrow(() -> new Exception("Product Not Found"));

            // SAVE PRODUCT
            int quantity = product.getStockQuantity() + orderDto.getQuantity();
            product.setStockQuantity(quantity);
            productRepository.save(product);

            // SAVE ORDER
            var order = orderRepository.findById(orderDto.getOrderId())
                    .orElseThrow(() -> new Exception("Order Not Found"));
            order.setOrderStatus("Cancel");
            orderRepository.save(order);

            // RESPONSE
            response.setStatus("success");
            return response;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public BaseRespone<List<SellProjection>> getTopSell() {
        BaseRespone<List<SellProjection>> response = new BaseRespone<>();
        try {
            List<SellProjection> sellDto = orderRepository.getTopSell();
            response.setStatus("success");
            response.setData(sellDto);
            return response;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public BaseRespone<List<SellProjection>> getLeastSell() {
        BaseRespone<List<SellProjection>> response = new BaseRespone<>();
        try {
            List<SellProjection> sellDto = orderRepository.getleastSell();
            response.setStatus("success");
            response.setData(sellDto);
            return response;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public BaseRespone<List<Map<String, Object>>> getRevenue() {
        BaseRespone<List<Map<String, Object>>> response = new BaseRespone<>();
        try {
            // GET REVENUE
            List<RevenueProjection> revenue = orderRepository.getRevenue();
            List<Map<String, Object>> revenueData = List.of(
                    Map.of("Revenue of Day", revenue.get(0)),
                    Map.of("Revenue of Month", revenue.get(revenue.size() - 1))
            );

            // RESPONSE
            response.setStatus("success");
            response.setData(revenueData);
            return response;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

}
