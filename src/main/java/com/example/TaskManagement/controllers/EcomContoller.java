package com.example.TaskManagement.controllers;

import com.example.TaskManagement.entities.Order;
import com.example.TaskManagement.services.otherService.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ABC")
@RequiredArgsConstructor
public class EcomContoller {

    private final OrderService orderService;

    @GetMapping("/ping")
    public String ping(){
        return "Ping Ecom";
    }


}
