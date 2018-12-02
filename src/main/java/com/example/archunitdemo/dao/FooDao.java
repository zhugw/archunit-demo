package com.example.archunitdemo.dao;

import com.example.archunitdemo.controller.Foo;
import com.example.archunitdemo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zhuguowei on 2018-12-01.
 */
@Repository
@Slf4j
public class FooDao {
    @Autowired
    private FooService fooService;
    public void save(Foo foo) {
        log.info("Save foo: {}", foo);
    }
}
