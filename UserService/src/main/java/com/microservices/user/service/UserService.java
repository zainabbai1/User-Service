package com.microservices.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.user.dto.OrderDto;
import com.microservices.user.dto.UserDto;
import com.microservices.user.dto.UserOrderDto;
import com.microservices.user.feignclient.OrderFeignClient;
import com.microservices.user.model.User;
import com.microservices.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderFeignClient orderFeignClient;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return new UserDto(userRepository.save(user).getId(), user.getName(), user.getEmail());
    }

    
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return new UserDto(userRepository.save(user).getId(), user.getName(), user.getEmail());
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    public List<OrderDto> getOrderForUser(Long userId) {
        return orderFeignClient.getOrderByUserId(userId);
    }
    
    
    public UserOrderDto getUserWithOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<OrderDto> orders = orderFeignClient.getOrderByUserId(userId);
        
        return new UserOrderDto(user.getId(), user.getName(), user.getEmail(), orders);
    }  
    
    


}
