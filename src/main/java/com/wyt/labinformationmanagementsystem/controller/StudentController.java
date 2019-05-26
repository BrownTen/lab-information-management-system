package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Report;
import com.wyt.labinformationmanagementsystem.model.db.Student;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
import com.wyt.labinformationmanagementsystem.model.vo.ReportCondition;
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
    public String findOrderRecordsLimitByStuId(@PathVariable Integer currentPage, @PathVariable Integer stuId, Model model){
        Integer currentCount = 8;
        PageBean<Report> pageBean = studentService.getReportsLimitByStuId(currentPage, currentCount, stuId);
        model.addAttribute("pageBean", pageBean);
        return "student/infos/orderRecord";
    }

    @GetMapping("/conditionReports/{currentPage}/{stuId}")
    public String findReportsLimitByCondition(@PathVariable Integer currentPage, @PathVariable Integer stuId, ReportCondition reportCondition, Model model){
        Integer currentCount = 8;
        PageBean<Report> pageBean = studentService.getReportsLimitByConditionByStuId(currentPage, currentCount, reportCondition, stuId);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("reportCondition", reportCondition);
        return "student/infos/orderRecord";
    }

    @GetMapping("/report/{reportId}")
    public String toReportContent(@PathVariable Integer reportId, Model model){
        Report report = studentService.getReportByReportId(reportId);
        model.addAttribute("report", report);
        return "student/infos/report";
    }

    @PutMapping("/report/{stuId}")
    public  String updateReportContent(@PathVariable Integer stuId, Report report){
        studentService.updateReportContent(report);
        return "redirect:/student/orders/1/"+stuId;
    }

}
