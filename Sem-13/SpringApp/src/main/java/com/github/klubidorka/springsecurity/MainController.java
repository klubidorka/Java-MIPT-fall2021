package com.github.klubidorka.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("")
public class MainController {
    static Set<String> strings = new HashSet<>();
    static String current = "abc";

    @GetMapping({"/start"})
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping({"/other"})
    public String other() {
        for (int i = 0; i < 5000; i++) {
            current += "h";
            strings.add(current);
        }
        return "Other page";
    }
}
