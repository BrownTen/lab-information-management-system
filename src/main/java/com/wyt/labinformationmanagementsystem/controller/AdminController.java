package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Group;
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
        Integer currentCount = 8;
        PageBean<Teacher> pageBean = adminService.getTeachersLimit(currentPage, currentCount);
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

    @GetMapping("/groups/{currentPage}")
    public String groupInfos(@PathVariable Integer currentPage, Model model){
        Integer currentCount = 8;
        PageBean<Group> pageBean = adminService.getGroupsLimit(currentPage, currentCount);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/group";
    }

    @GetMapping("/group/{groupId}")
    public String toGroupEditPage(@PathVariable Integer groupId, Model model){
        Group group = adminService.getGroupByGroupId(groupId);
        model.addAttribute("group", group);
        return "admin/addOrEdit/group";
    }

    @PutMapping("/group")
    public String updateGroupInfo(Group group){
        adminService.updateGroupInfo(group);
        return "redirect:/admin/groups/1";
    }

    @DeleteMapping("/group/{groupId}")
    public String deleteGroupInfo(@PathVariable Integer groupId){
        adminService.deleteGroupInfoByGroupId(groupId);
        return "redirect:/admin/groups/1";
    }

    @GetMapping("/group")
    public String toGroupAddPage(){
        return "admin/addOrEdit/group";
    }

    @PostMapping("/group")
    public String insertTeacher(Group group){
        adminService.insertGroup(group);
        return "redirect:/admin/groups/1";
    }

    @GetMapping("/conditionGroups/{currentPage}")
    public String findTeacherInfosByCondition(@PathVariable Integer currentPage, Group group, Model model){
        Integer currentCount = 8 ;
        PageBean<Group> pageBean = adminService.getGroupsLimitByCondition(currentPage, currentCount, group);
        model.addAttribute("group", group);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/group";
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
}
