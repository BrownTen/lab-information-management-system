package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.PageBean;
import com.wyt.labinformationmanagementsystem.model.Teacher;
import com.wyt.labinformationmanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/labs")
    public String labInfos(){
        //TODO
        return "admin/infos/lab";
    }

    @GetMapping("/teachers/{currentPage}")
    public String teacherInfos(@PathVariable Integer currentPage, Model model){
        Integer currentCount = 10;
        PageBean<Teacher> pageBean = adminService.getTeachersLimits(currentPage, currentCount);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/teacher";
    }

    @GetMapping("/stus")
    public String stuInfos(){
        //TODO
        return "admin/infos/stu";
    }

    @DeleteMapping("/teacher/{id}")
    public String teacherInfoDelete(@PathVariable Integer id){
        adminService.deleteTeacherInfoByTeacherId(id);
        return "redirect:/admin/teachers/1";
    }
}
