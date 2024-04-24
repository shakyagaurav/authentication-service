package com.demo.service;

import com.demo.dto.AuthRequest;
import com.demo.dto.AuthResponse;
import com.demo.entity.User;
import com.demo.exception.ResourceAlreadyExistException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.UserRepository;
import com.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public User addUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new ResourceAlreadyExistException("Username " + user.getUsername() + " already exists");
        }
        return userRepository.save(user);
    }

    public AuthResponse loginUser(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Username " + authRequest.getUsername() + " not found"));
        return new AuthResponse(jwtUtil.generateToken(user), user.getUsername(), user.getEmail(),
                user.getRoles());
    }

    public User findByUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Id " + id + " not found"));
    }
}
