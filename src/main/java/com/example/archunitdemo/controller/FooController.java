package com.example.archunitdemo.controller;

import com.example.archunitdemo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuguowei on 2018-12-01.
 */
@RestController
@RequestMapping("/foos")
@Slf4j
public class FooController {
    @Autowired
    private FooService fooService;
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody Foo foo) {
        log.info("Create foo: {}", foo);
        System.out.println(foo);
        fooService.create(foo);
    }
}
