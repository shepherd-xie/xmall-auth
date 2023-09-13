package com.orkva.projects.xmall.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author Shepherd Xie
 * @version 2023/9/13
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "hello";
    }

}
