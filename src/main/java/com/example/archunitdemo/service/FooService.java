package com.example.archunitdemo.service;

import com.example.archunitdemo.controller.Foo;
import com.example.archunitdemo.controller.FooController;
import com.example.archunitdemo.dao.FooDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhuguowei on 2018-12-01.
 */
@Service
public class FooService {

    @Autowired
    private FooDao fooDao;
    @Autowired
    private FooController fooController;

    public void create(Foo foo) {
        fooDao.save(foo);
    }
}
