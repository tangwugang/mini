package com.sanweiboo.lingdu.sys.service.impl;

import com.sanweibook.lingdu.sys.service.SysService;
import org.springframework.stereotype.Component;

/**
 * Created by twg on 16/10/9.
 */
@Component
public class SysServiceImpl implements SysService {
    @Override
    public void test() {
        System.out.print("====this is sys test====");
    }
}
