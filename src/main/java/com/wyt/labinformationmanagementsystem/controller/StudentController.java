package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public String toStudentSelfInfo(){
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
