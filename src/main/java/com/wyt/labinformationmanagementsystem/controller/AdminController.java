package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
import com.wyt.labinformationmanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String deleteTeacherInfo(@PathVariable Integer id){
        adminService.deleteTeacherInfoByTeacherId(id);
        return "redirect:/admin/teachers/1";
    }

    @GetMapping("/teacher/{id}")
    public String toTeacherEditPage(@PathVariable Integer id, Model model){
        Teacher teacher = adminService.findTeacherByTeacherId(id);
        model.addAttribute("teacher", teacher);
        return "admin/addOrEdit/teacher";
    }

    @PutMapping("/teacher")
    public String updateTeacherInfo(Teacher teacher){
        adminService.updateTeacherInfo(teacher);
        return "redirect:/admin/teachers/1";
    }

    @GetMapping("/teachers/{currentPage}")
    public String findAllTeacherInfos(@PathVariable Integer currentPage, Model model){
        Integer currentCount = 10;
        PageBean<Teacher> pageBean = adminService.getTeachersLimits(currentPage, currentCount);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/teacher";
    }

    @GetMapping("/teachers")
    public String findTeacherInfosByCondition(Teacher teacher, Model model){

        if ((teacher.getTeacherName()==null && teacher.getTeacherNumber()==null)
            || ("".equals(teacher.getTeacherNumber()) && "".equals(teacher.getTeacherName()))){
            return "redirect:/admin/teachers/1";
        }

        List<Teacher> teacherList = adminService.getTeachersByCondition(teacher);
        PageBean<Teacher> pageBean = new PageBean<>();
        pageBean.setList(teacherList);
        model.addAttribute("conditionParam", teacher);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/teacher";
    }

    @GetMapping("/labs/{currentPage}")
    public String labInfos(@PathVariable Integer currentPage, Model model){
        //TODO
        return "admin/infos/lab";
    }

    @GetMapping("/stus/{currentPage}")
    public String stuInfos(@PathVariable Integer currentPage, Model model){
        //TODO
        return "admin/infos/stu";
    }

    @GetMapping("/orders/{currentPage}")
    public String orderInfos(@PathVariable Integer currentPage, Model model){
        //TODO
        return "admin/infos/order";
    }

    @GetMapping("/courses/{currentPage}")
    public String courseInfos(@PathVariable Integer currentPage, Model model){
        //TODO
        return "admin/infos/course";
    }

    @GetMapping("/groups/{currentPage}")
    public String groupInfos(@PathVariable Integer currentPage, Model model){
        //TODO
        return "group";
    }
}
