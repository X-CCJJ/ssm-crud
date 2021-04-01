package com.ssm.service;

import com.ssm.bean.Employee;
import com.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getAll(){
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        return employees;
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public Employee checkEmpName(String empName) {
        Employee employee = employeeMapper.selectByEmpName(empName);
        return employee;
    }

    public boolean testEmpName(String EmpName){
        String EmpNamePatter = "^[\\u4e00-\\u9fa5a-zA-Z0-9_-]{2,16}$";
        return EmpName.matches(EmpNamePatter);
    }

    public boolean testEmail(String Email){
        String EmailPatter = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        return Email.matches(EmailPatter);
    }

    public Employee getEmp(Integer empId){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(empId);
        return employee;
    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public void deleteEmp(Integer empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }


    public void deleteEmpBatch(List<Integer> empsIdlist) {
        employeeMapper.deleteBatch(empsIdlist);
    }
}
