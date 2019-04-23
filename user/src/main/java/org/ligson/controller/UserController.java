package org.ligson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/test")
    public String test() throws Exception {
        Thread.sleep(2 * 60 * 1000);
        return "ok";
    }
}
