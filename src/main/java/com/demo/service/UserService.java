package com.demo.service;

import com.demo.dto.AuthResponse;
import com.demo.entity.User;
import com.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;

    private final List<User> userList=Arrays.asList(new User(1L, "Gaurav", "gaurav@gmail.com", "gaurav@gmail.com", Arrays.asList("ADMIN","USER")),
            new User(2L, "Rajesh", "rajesh@gmail.com", "rajesh@gmail.com", Arrays.asList("ADMIN","USER")),
            new User(3L, "karthik", "karthik@gmail.com", "karthik@gmail.com", List.of("USER")));

    public User addUser() {
        return userList.get(new Random().nextInt(userList.size()));
    }

    public AuthResponse loginUser() {
        User user= userList.get(new Random().nextInt(userList.size()));
        return new AuthResponse(jwtUtil.generateToken(user), user.getUsername(), user.getEmail(),
                user.getRoles());
    }

    public User findUserById(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(userList.get(new Random().nextInt(userList.size())));
    }
}
