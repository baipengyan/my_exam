package com.exam.controller;

import com.exam.annotation.Log;
import com.exam.entity.Notice;
import com.exam.service.NoticeService;
import com.exam.service.SysUserService;
import com.exam.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: IndexController
 * @description:
 * @auther: Dimple
 * @date: 04/29/19
 * @version: 1.0
 */
@Controller
public class IndexController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    NoticeService noticeService;

    @Log
    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("notices", noticeService.findNoticeList(new Notice()));
        return "main";
    }


    @Log("获取用户信息")
    @GetMapping("/userInfo")
    public String userInfo(Model model) {
        UserRealm.ShiroUser principal = (UserRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        Integer id = principal.getId();
        model.addAttribute("user", sysUserService.selectSysyUserById(id));
        return "index/userInfo";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        UserRealm.ShiroUser principal = (UserRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        Integer id = principal.getId();
        model.addAttribute("user", sysUserService.selectSysyUserById(id));
        return "index/changePassword";
    }


}
