package com.microservices.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.microservices.user.dto.OrderDto;


//@FeignClient(name = "Order-Service", url = "${order.service.url}")
@FeignClient(name = "OrderService")
public interface OrderFeignClient {

	    @GetMapping("/orders/{userId}")
	    List<OrderDto> getOrderByUserId(@PathVariable("userId") Long userId);
	}


