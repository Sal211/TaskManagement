package com.example.TaskManagement.utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseRespone<T> {

    @Builder.Default
    String status = "fail";
    @Builder.Default
    String errMsg = "";

    T Data;
}
