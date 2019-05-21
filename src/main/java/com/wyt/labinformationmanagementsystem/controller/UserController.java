package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Admin;
import com.wyt.labinformationmanagementsystem.model.db.Student;
import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("number") String number,
                        @RequestParam("password") String password,
                        @RequestParam("role") String role,
                        Map<String, Object> map, HttpSession session){
        if("".equals(role)){
            map.put("msg","请选择角色");
            return "login";
        }
        if("admin".equals(role)){
            Admin admin = userService.adminLogin(number);
            if(admin!=null && admin.getAdmPassword().equals(password)){
                session.setAttribute("loginUser",admin);
                return "redirect:/admin_main.html";
            }
        } else if("student".equals(role)){
            Student student = userService.stuentLogin(number);
            if(student!=null && student.getStuPassword().equals(password)){
                session.setAttribute("loginUser",student);
                return "redirect:/stu_main.html";
            }
        } else if("teacher".equals(role)){
            Teacher teacher = userService.teacherLogin(number);
            if(teacher!=null && teacher.getTeacherPassword().equals(password)){
                session.setAttribute("loginUser",teacher);
                return "redirect:/teacher_main.html";
            }
        }
        map.put("msg","登录失败");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "login";
    }
}
