package com.sanweibook.lingdu.sys.service.test;

import com.sanweibook.lingdu.sys.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Created by twg on 16/10/9.
 */
@ContextConfiguration(locations = {"classpath:spring-init.xml"})
public class SysTest extends AbstractTestNGSpringContextTests {
    @Resource
    SysService sysService;

    @BeforeClass
    public void beforeClass(){
        System.out.println("=== This is beforeClass ===");
    }

    @BeforeMethod
    public void beforeTest(){
        System.out.println("=== This is beforeTest ===");
    }

    @Test
    public void test(){
        sysService.test();
    }

}
