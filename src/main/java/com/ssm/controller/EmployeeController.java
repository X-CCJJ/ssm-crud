package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Employee;
import com.ssm.bean.Msg;
import com.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 处理员工crud请求
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    // 未使用json
    /*
        @RequestMapping("/emps")
        public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
            // 传入页码以及每一页显示的条数
            PageHelper.startPage(pn, 5);
            // startPage后面的查询就是分页查询
            List<Employee> employees = employeeService.getAll();
            // 使用pageInfo包装查询后的结果，只需将pageInfo交给页面
            // pageInfo封装了详细的页面信息，navicatePages为连续显示的页数
            PageInfo pageInfo = new PageInfo(employees,5);
            model.addAttribute("pageInfo",pageInfo);
            return "list";
        }
     */

    // 分页功能 查询所有员工
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 传入页码以及每一页显示的条数
        PageHelper.startPage(pn, 5);
        // startPage后面的查询就是分页查询
        List<Employee> employees = employeeService.getAll();
        // 使用pageInfo包装查询后的结果，只需将pageInfo交给页面
        // pageInfo封装了详细的页面信息，navicatePages为连续显示的页数
        PageInfo pageInfo = new PageInfo(employees, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }

    // 保存员工信息
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmpWithJson(Employee employee) {
        // 后端验证
        HashMap<String, Object> map = new HashMap<>();
        if(!employeeService.testEmpName(employee.getEmpName())){
            map.put("empNameError", "用户名不合法！");
            return Msg.fail().add("errorField", map);
        }
        if(!employeeService.testEmail(employee.getEmail())){
            map.put("emailError", "邮箱不合法！");
            return Msg.fail().add("errorField", map);
        }
        if(employeeService.checkEmpName(employee.getEmpName()) != null){
            map.put("empNameError", "用户名重复！");
            return Msg.fail().add("errorField", map);
        }
        employeeService.saveEmp(employee);
        return Msg.success();
    }

    // 查询员工姓名是否重复
    @RequestMapping("/checkName")
    @ResponseBody
    public Msg checkEmpName(@RequestParam("empName") String empName) {
        Employee employee = employeeService.checkEmpName(empName);
        if (employee == null) {
            return Msg.success();
        } else return Msg.fail();
    }

    // 通过员工id查询员工信息
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    // 更新员工信息
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    // 删除员工信息
    @RequestMapping(value = "/emp/{empsId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("empsId") String empsId){
        if(empsId.contains("-")){
            // 批量删除员工信息
            String[] empsIdArray = empsId.split("-");
            List<Integer> empsIdlist = new ArrayList<>();
            for (String s : empsIdArray) {
                empsIdlist.add(Integer.parseInt(s));
            }
            employeeService.deleteEmpBatch(empsIdlist);
        }else{
            // 单个删除员工信息
            Integer empId = Integer.parseInt(empsId);
            employeeService.deleteEmp(empId);
        }
        return Msg.success();
    }
}
