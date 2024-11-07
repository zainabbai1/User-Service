package com.microservices.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.dto.OrderDto;
import com.microservices.user.dto.UserDto;
import com.microservices.user.dto.UserOrderDto;
import com.microservices.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }


    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
    
    
    //FeignClient call to order service to get the order details along with user id
    @GetMapping("/{userId}/order")
    public List<OrderDto> getOrderForUser(@PathVariable("userId") Long userId) {
        return userService.getOrderForUser(userId);
    }

    //FeignClient call to order service to get the order details along with user details
    @GetMapping("/{userId}/details")
    public UserOrderDto getUserDetailsWithOrders(@PathVariable("userId") Long userId) {
        return userService.getUserWithOrders(userId);
    }
}



