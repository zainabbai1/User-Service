package com.microservices.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDto {

	private Long userId;
    private String name;
    private String email;
    private List<OrderDto> orders;
}
