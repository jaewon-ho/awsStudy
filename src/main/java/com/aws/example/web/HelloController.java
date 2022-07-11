package com.aws.example.web;

import com.aws.example.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "amount", required = false, defaultValue = "0") int amount
            ){

        return new HelloResponseDto(name, amount);
    }
}
