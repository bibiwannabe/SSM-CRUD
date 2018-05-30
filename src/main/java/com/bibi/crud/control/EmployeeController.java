package com.bibi.crud.control;


import com.bibi.crud.bean.Employee;
import com.bibi.crud.bean.Msg;
import com.bibi.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by bibi on 2018/5/14.
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn){

        //引入PageHelper分页插件
        //查询之前调用即可实现下面查询的分页
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        //使用pageinfo包装 list
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);

    }

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            Map<String, Object> map = new HashMap<String, Object>();
            List<FieldError> errors =  result.getFieldErrors();
            for(FieldError fieldError:errors){
                System.out.println("错误字段名"+fieldError.getField());
                System.out.println("错误信息"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    @RequestMapping("/checkuser")
    @ResponseBody
    public Msg checkuser(String empName){

        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(empName.matches(regx)){
            return Msg.fail().add("va_msg","用户名必须是6-16位数字和字母的选择");
        }
        boolean b =  employeeService.checkUser(empName);
        if(b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","用户名不可用");
        }
    }


    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer empId){

        Employee employee =  employeeService.getEmp(empId);
        return Msg.success().add("emp",employee);
    }

    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){

        employeeService.updateEmp(employee);
        //System.out.println(employee.getEmail()+employee.getGender());
        return Msg.success();
    }
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids")String ids){
        if(ids.contains("-")){
            String[] str_ids =  ids.split("-");
            List<Integer> del_ids = new ArrayList<Integer>();
            for(String id:str_ids){
                del_ids.add(Integer.parseInt(id));
            }
            employeeService.deleteBatch(del_ids);
            return Msg.success();
        }else {
            employeeService.deleteEmp(Integer.parseInt(ids));
            return Msg.success();
        }
    }






}
