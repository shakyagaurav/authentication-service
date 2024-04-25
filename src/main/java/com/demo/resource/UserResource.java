package com.demo.resource;

import com.demo.dto.Payment;
import com.demo.entity.User;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
//@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    private WebClient webClient;

    public UserResource(UserService userService) {
        this.userService=userService;
        // Initialize WebClient
        this.webClient = WebClient.builder().baseUrl("http://localhost:8082").build();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findByUser(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Mono<Payment>> callPayment(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        log.info("call payment service from authentication service and get token: {}", token);
        // Set JWT token as a bearer token
        WebClient.RequestHeadersSpec<?> requestSpec = webClient.post()
                .uri("/api/v1/payment/create")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return new ResponseEntity<>(requestSpec
                .retrieve()
                .bodyToMono(Payment.class), HttpStatus.OK);
    }
}
