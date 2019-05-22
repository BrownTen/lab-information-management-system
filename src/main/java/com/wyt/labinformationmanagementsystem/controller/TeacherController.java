package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Course;
import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
import com.wyt.labinformationmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping
    public String toTeacherSelfInfo(){
        return "teacher/infos/self";
    }

    @PutMapping
    public String updateTeacher(Teacher teacher,
                                Model model,
                                HttpSession session){
        teacherService.updateTeacher(teacher);
        session.setAttribute("loginUser",teacher);
        model.addAttribute("msg","信息修改成功");
        return "self";
    }

    @GetMapping("/courses/{currentPage}/{teacherId}")
    public String findAllCoursesByTeacherId(@PathVariable Integer currentPage,
                                            @PathVariable Integer teacherId,
                                            Model model){
        Integer currentCount = 10;
        PageBean<Course> pageBean = teacherService.getCoursesLimitsByTeacherId(currentPage, currentCount, teacherId);
        model.addAttribute("pageBean", pageBean);
        return "teacher/infos/course";
    }

    @GetMapping("/labs")
    public String findAllLabs(){
        return "teacher/infos/orderLab";
    }

    @GetMapping("/orderedLabs")
    public String findAllOrderedLabs(){
        return "teacher/infos/orderRecord";
    }

    @GetMapping("/reports")
    public String findAllReports(){
        return "teacher/infos/report";
    }
}
