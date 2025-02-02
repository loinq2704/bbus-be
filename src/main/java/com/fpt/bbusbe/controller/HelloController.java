package com.fpt.bbusbe.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @Operation(summary = "Test API", description = "Description for API")
    @GetMapping("/hello")
    public String greet(@RequestParam String name) {
        return "Hello " + name + "!";
    }
}
