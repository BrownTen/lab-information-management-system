package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.PageBean;
import com.wyt.labinformationmanagementsystem.model.Teacher;
import com.wyt.labinformationmanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/teacher")
    public String toTeacherAddPage(){
        return "admin/addOrEdit/teacher";
    }

    @PostMapping("/teacher")
    public String insertTeacher(Teacher teacher){
        adminService.insertTeacher(teacher);
        return "redirect:/admin/teachers/1";
    }

    @DeleteMapping("/teacher/{id}")
    public String teacherInfoDelete(@PathVariable Integer id){
        adminService.deleteTeacherInfoByTeacherId(id);
        return "redirect:/admin/teachers/1";
    }

    @GetMapping("/teachers/{currentPage}")
    public String teacherInfos(@PathVariable Integer currentPage, Model model){
        Integer currentCount = 10;
        PageBean<Teacher> pageBean = adminService.getTeachersLimits(currentPage, currentCount);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/teacher";
    }

    @GetMapping("/labs")
    public String labInfos(){
        //TODO
        return "admin/infos/lab";
    }

    @GetMapping("/stus")
    public String stuInfos(){
        //TODO
        return "admin/infos/stu";
    }
}
