package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Group;
import com.wyt.labinformationmanagementsystem.model.db.Lab;
import com.wyt.labinformationmanagementsystem.model.db.Student;
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
    public String insertGroup(Group group){
        adminService.insertGroup(group);
        return "redirect:/admin/groups/1";
    }

    @GetMapping("/conditionGroups/{currentPage}")
    public String findGroupInfosByCondition(@PathVariable Integer currentPage, Group group, Model model){
        Integer currentCount = 8 ;
        PageBean<Group> pageBean = adminService.getGroupsLimitByCondition(currentPage, currentCount, group);
        model.addAttribute("group", group);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/group";
    }

    @GetMapping("/labs/{currentPage}")
    public String labInfos(@PathVariable Integer currentPage, Model model){
        Integer currentCount = 8;
        PageBean<Lab> pageBean = adminService.getLabsLimit(currentPage, currentCount);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/lab";
    }

    @GetMapping("/lab/{labId}")
    public String toLabEditPage(@PathVariable Integer labId, Model model){
        Lab lab = adminService.getLabByLabId(labId);
        model.addAttribute("lab", lab);
        return "admin/addOrEdit/lab";
    }

    @PutMapping("/lab")
    public String updateLabInfo(Lab lab){
        adminService.updateLabInfo(lab);
        return "redirect:/admin/labs/1";
    }

    @DeleteMapping("/lab/{labId}")
    public String deleteLabInfo(@PathVariable Integer labId){
        adminService.deleteLabInfoByLabId(labId);
        return "redirect:/admin/labs/1";
    }

    @GetMapping("/lab")
    public String toLabAddPage(){
        return "admin/addOrEdit/lab";
    }

    @PostMapping("/lab")
    public String insertLab(Lab lab){
        adminService.insertLab(lab);
        return "redirect:/admin/labs/1";
    }

    @GetMapping("/conditionLabs/{currentPage}")
    public String findLabInfosByCondition(@PathVariable Integer currentPage, Lab lab, Model model){
        Integer currentCount = 8 ;
        PageBean<Lab> pageBean = adminService.getLabsLimitByCondition(currentPage, currentCount, lab);
        model.addAttribute("lab", lab);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/lab";
    }

    @GetMapping("/stus/{currentPage}")
    public String stuInfos(@PathVariable Integer currentPage, Model model){
        Integer currentCount = 8;
        PageBean<Student> pageBean = adminService.getStusLimit(currentPage, currentCount);
        model.addAttribute("pageBean", pageBean);
        return "admin/infos/stu";
    }

    @GetMapping("/stu/{stuId}")
    public String toStuEditPage(@PathVariable Integer stuId, Model model){
        Student stu = adminService.getStuByStuId(stuId);
        model.addAttribute("stu", stu);
        return "admin/addOrEdit/stu";
    }

    @PutMapping("/stu")
    public String updateStuInfo(Student stu, Model model){
        Integer groupId = adminService.getGroupbyGroupName(stu.getGroup().getGroupName());
        if (groupId == null){
            model.addAttribute("msg","请输入准确班级");
            return "admin/addOrEdit/stu";
        }
        stu.getGroup().setGroupId(groupId);

        adminService.updateStuInfo(stu);
        return "redirect:/admin/stus/1";
    }

    @DeleteMapping("/stu/{stuId}")
    public String deleteStuInfo(@PathVariable Integer stuId){
        adminService.deleteStuInfoByStuId(stuId);
        return "redirect:/admin/stus/1";
    }

    @GetMapping("/stu")
    public String toStuAddPage(){
        return "admin/addOrEdit/stu";
    }

    @PostMapping("/stu")
    public String insertStu(Student stu, Model model){

        Integer groupId = adminService.getGroupbyGroupName(stu.getGroup().getGroupName());
        if (groupId == null){
            model.addAttribute("msg","请输入准确班级");
            return "admin/addOrEdit/stu";
        }
        stu.getGroup().setGroupId(groupId);

        adminService.insertStu(stu);
        return "redirect:/admin/stus/1";
    }

    @GetMapping("/conditionStus/{currentPage}")
    public String findStuInfosByCondition(@PathVariable Integer currentPage, Student stu, Model model){
        Integer currentCount = 8 ;
        PageBean<Student> pageBean = adminService.getStusLimitByCondition(currentPage, currentCount, stu);
        model.addAttribute("stu", stu);
        model.addAttribute("pageBean", pageBean);
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
