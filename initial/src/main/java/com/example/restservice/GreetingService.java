package com.example.restservice;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greet() {
        return "Hello, this is a service!";
    }
}
