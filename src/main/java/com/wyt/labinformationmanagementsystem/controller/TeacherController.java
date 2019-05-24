package com.wyt.labinformationmanagementsystem.controller;

import com.wyt.labinformationmanagementsystem.model.db.Course;
import com.wyt.labinformationmanagementsystem.model.db.Order;
import com.wyt.labinformationmanagementsystem.model.db.Teacher;
import com.wyt.labinformationmanagementsystem.model.vo.OrderRecordCondition;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
import com.wyt.labinformationmanagementsystem.service.TeacherService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

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
    public String updateTeacher(Teacher teacher, Model model, HttpSession session){
        teacherService.updateTeacher(teacher);
        session.setAttribute("loginUser",teacher);
        model.addAttribute("msg","信息修改成功");
        return "teacher/infos/self";
    }

    @GetMapping("/courses/{currentPage}/{teacherId}")
    public String findCoursesLimitByTeacherId(@PathVariable Integer currentPage,
                                              @PathVariable Integer teacherId,
                                              Model model){
        Integer currentCount = 8;
        PageBean<Course> pageBean = teacherService.getCoursesLimitByTeacherId(currentPage, currentCount, teacherId);
        model.addAttribute("pageBean", pageBean);
        return "teacher/infos/course";
    }

    @GetMapping("/courses/{teacherId}")
    public String findCoursesByCondition(@PathVariable Integer teacherId,
                                         @RequestParam("courseName") String courseName,
                                         @RequestParam("groupName") String  groupName,
                                         Model model){

        if (Strings.isEmpty(courseName) && Strings.isEmpty(groupName)){
            return "redirect:/teacher/courses/1/"+teacherId;
        }

        List<Course> courseList = teacherService.getCoursesByCondition(courseName, groupName, teacherId);
        PageBean<Course> pageBean = new PageBean<>();
        pageBean.setList(courseList);

        List<String> conditionBody = Arrays.asList(courseName, groupName);

        model.addAttribute("conditionBody", conditionBody);
        model.addAttribute("pageBean", pageBean);
        return "teacher/infos/course";
    }

    @GetMapping("/order/{orderId}/{teacherId}")
    public String toOrderLabSelectGroup(@PathVariable Integer orderId, @PathVariable Integer teacherId, HttpSession session){
        session.setAttribute("orderId", orderId);
        return "redirect:/teacher/courses/1/"+teacherId;
    }

    @PutMapping("/order/{orderId}/{courseId}/{teacherId}")
    public String updateOrderStatus2ByOrderId(@PathVariable Integer orderId,
                                             @PathVariable Integer courseId,
                                             @PathVariable Integer teacherId,
                                             HttpSession session){
        teacherService.updateOrderStatus2ByOrderId(orderId, courseId);
        if(session.getAttribute("orderId")!=null){
            session.removeAttribute("orderId");
        }
        return "redirect:/teacher/orderedLabs/1/"+teacherId;
    }

    @GetMapping("/orderLabs/{currentPage}")
    public String findLabsLimitStatus0(@PathVariable Integer currentPage,
                                       Model model, HttpSession session){
        if(session.getAttribute("orderId")!=null){
            session.removeAttribute("orderId");
        }
        Integer currentCount = 8;
        PageBean<Order> pageBean = teacherService.getOrdersLimitStatus0(currentPage, currentCount);
        model.addAttribute("pageBean", pageBean);
        return "teacher/infos/orderLab";
    }

    @GetMapping("/conditionOrderLabs/{currentPage}")
    public String findLabsLimitStatus0ByCondition(@PathVariable Integer currentPage,Order order, Model model){
        if (order.getOrderDate()==null && order.getOrderTime()==null){
            return "redirect:/teacher/orderLabs/1";
        }

        Integer currentCount = 8;
        PageBean<Order> pageBean = teacherService.getOrdersLimitStatus0ByCondition(currentPage, currentCount, order.getOrderDate(), order.getOrderTime());
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("conditionParm", order);
        return "teacher/infos/orderLab";
    }

    @GetMapping("/orderedLabs/{currentPage}/{teacherId}")
    public String findOrderedLabsLimitStatus123ByTeacherId(@PathVariable Integer currentPage,
                                                           @PathVariable Integer teacherId,
                                                           Model model){
        Integer currentCount = 8;
        PageBean<Order> pageBean = teacherService.getOrderedLabsLimitStatus123ByTeacherId(currentPage, currentCount, teacherId);
        model.addAttribute("pageBean", pageBean);
        return "teacher/infos/orderRecord";
    }

    @GetMapping("/conditionOrderRecords/{currentPage}/{teacherId}")
    public String findOrderRecordsLimitByCondition(@PathVariable Integer currentPage,
                                                   @PathVariable Integer teacherId,
                                                   OrderRecordCondition orderRecordCondition,
                                                   Model model){
        Integer currentCount = 8;
        PageBean<Order> pageBean = teacherService.getOrderedLabsLimitByConditionByTeacherId(currentPage, currentCount, orderRecordCondition, teacherId);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("orderRecordCondition",orderRecordCondition);
        return "teacher/infos/orderRecord";
    }

    @PostMapping("/orderRecord/{orderId}/{teacherId}")
    public String updateOrderRecordStatus3ByOrderId(@PathVariable Integer orderId, @PathVariable Integer teacherId, String orderMessage){
        teacherService.updateOrderStatus3ByOrderId(orderId, orderMessage);
        Order order = teacherService.findOrderByOrderId(orderId);
        teacherService.insertOrder(order);
        return "redirect:/teacher/orderedLabs/1/"+teacherId;
    }

    @GetMapping("/reports")
    public String findAllReports(){
        return "teacher/infos/report";
    }
}
