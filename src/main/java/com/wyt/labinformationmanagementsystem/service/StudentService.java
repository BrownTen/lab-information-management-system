package com.wyt.labinformationmanagementsystem.service;

import com.wyt.labinformationmanagementsystem.mapper.ReportMapper;
import com.wyt.labinformationmanagementsystem.mapper.StudentMapper;
import com.wyt.labinformationmanagementsystem.model.db.Report;
import com.wyt.labinformationmanagementsystem.model.db.Student;
import com.wyt.labinformationmanagementsystem.model.vo.PageBean;
import com.wyt.labinformationmanagementsystem.model.vo.ReportCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ReportMapper reportMapper;

    public void updateStudent(Student student) {
        studentMapper.updateStudentInfo(student);
    }

    public PageBean<Report> getReportsLimitByStuId(Integer currentPage, Integer currentCount, Integer stuId) {
        PageBean<Report> pageBean = new PageBean<>();

        Integer totalCount = 0;
        totalCount = reportMapper.getReportTotalCountByStuId(stuId);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Report> reports = reportMapper.getReportsLimitByStuId(index, currentCount, stuId);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(reports);

        return pageBean;

    }

    public PageBean<Report> getReportsLimitByConditionByStuId(Integer currentPage, Integer currentCount, ReportCondition reportCondition, Integer stuId) {
        PageBean<Report> pageBean = new PageBean<>();

        String formatDate = null;
        if(reportCondition.getOrderDate()!=null){
            formatDate =  new SimpleDateFormat("yyyy-MM-dd").format(reportCondition.getOrderDate());
        }

        Integer totalCount = 0;
        totalCount = reportMapper.getTotalCountByConditionByStuId(reportCondition, stuId, formatDate);

        Integer totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);

        Integer index = (currentPage - 1) * currentCount;
        List<Report> reports = reportMapper.getReportsLimitByConditionByStuId(index, currentCount, reportCondition, stuId, formatDate);

        pageBean
                .setCurrentPage(currentPage)
                .setCurrentCount(currentCount)
                .setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(reports);

        return pageBean;
    }

    public Report getReportByReportId(Integer reportId) {
        return reportMapper.getReportByReportId(reportId);
    }

    public void updateReportContent(Report report) {
        reportMapper.updateReportContent(report);
    }
}
