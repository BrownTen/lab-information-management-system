package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Student;
import com.wyt.labinformationmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public String toStudentSelfInfo(){
        return "student/infos/self";
    }

    @PutMapping
    public String updateStudent(Student student, Model model, HttpSession session){
        studentService.updateStudent(student);
        session.setAttribute("loginUser", student);
        model.addAttribute("msg", "信息修改成功");
        return "student/infos/self";
    }

    @GetMapping("/orders/{currentPage}/{stuId}")
    public String findOrderRecordsLimitByStuId(@PathVariable Integer currentPage, @PathVariable Integer stuId){
        //TODO
        return "student/infos/orderRecord";
    }

    @GetMapping("/reports/{currentPage}/{stuId}")
    public  String findReportsLimitByStuId(@PathVariable Integer currentPage, @PathVariable Integer stuId){
        //TODO
        return "student/infos/report";
    }

}
