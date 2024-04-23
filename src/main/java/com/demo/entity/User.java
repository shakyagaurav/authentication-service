package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String username;
    private List<String> roles;
}
