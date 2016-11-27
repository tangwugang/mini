package com.sanweibook.lingdu.sys;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by twg on 16/11/2.
 */
@Controller
@RequestMapping("/admin/home")
public class HomeController {


    @RequestMapping
    @RequiresRoles("admin1")
    public String home(Model model){
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("userName",subject.getPrincipals());
        return "home";
    }
}
