package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PutMapping
    public String updateTeacher(Teacher teacher,
                                Model model,
                                HttpSession session){
        teacherService.updateTeacher(teacher);
        session.setAttribute("loginUser",teacher);
        model.addAttribute("msg","信息修改成功");
        return "self";
    }

}
