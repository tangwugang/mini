package com.sanweibook.lingdu.model.sysUser;

import com.sanweibook.lingdu.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by twg on 16/11/1.
 */
@Getter
@Setter
public class SysUser extends BaseModel {
    private String userName;
    private String password;
}
