package com.exam.controller;

import com.exam.annotation.Log;
import com.exam.entity.Exam;
import com.exam.entity.SysUser;
import com.exam.service.ExamService;
import com.exam.service.SysUserService;
import com.exam.utils.web.AjaxResult;
import com.exam.utils.web.BaseController;
import com.exam.utils.web.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试卷管理
 */
@Controller
@RequestMapping("onlineExam/exam")
public class ExamController extends BaseController {
    //插入字符
    @Autowired
    private ExamService examService;
    @Autowired
    SysUserService sysUserService;

    @GetMapping()
    public String exam() {
        return "onlineExam/exam/exam";
    }

    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Exam exam) {
        startPage();
        List<Exam> exams = examService.findExamList(exam);
        return getDataTable(exams);
    }

    @GetMapping("/add")
    public String add(Model model) {
        SysUser sysUser = new SysUser();
        sysUser.setUserType("2");
        model.addAttribute("trachers", sysUserService.selectUserList(sysUser));
        sysUser.setUserType("3");
        model.addAttribute("students", sysUserService.selectUserList(sysUser));
        return "onlineExam/exam/add";
    }

    @Log("添加试卷")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Exam exam) {
        return toAjax(examService.insert(exam));
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        Exam exam = examService.queryById(id);
        model.addAttribute("exam", exam);
        SysUser sysUser = new SysUser();
        sysUser.setUserType("2");
        model.addAttribute("trachers", sysUserService.selectUserList(sysUser));
        sysUser.setUserType("3");
        List<SysUser> studentList = sysUserService.selectUserList(sysUser);
        Integer[] studentIds = exam.getStudentIds();
        for (SysUser user : studentList) {
            for (Integer studentId : studentIds) {
                if (studentId.equals(user.getId())) {
                    user.setStudentCheckFlag(true);
                }
            }
        }
        model.addAttribute("students", studentList);
        return "onlineExam/exam/update";
    }

    @Log("修改试卷")
    @PutMapping("/update")
    @ResponseBody
    public AjaxResult updateSave(Exam exam) {
        return toAjax(examService.update(exam));
    }

    @Log("删除试卷")
    @DeleteMapping()
    @ResponseBody
    public AjaxResult delete(String ids) {
        return toAjax(examService.deleteByIds(ids));
    }
}