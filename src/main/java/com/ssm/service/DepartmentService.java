package com.ssm.service;

import com.ssm.bean.Department;
import com.ssm.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    public List<Department> getAllDepts() {
        List<Department> departments = departmentMapper.selectDept();
        return departments;
    }


}
