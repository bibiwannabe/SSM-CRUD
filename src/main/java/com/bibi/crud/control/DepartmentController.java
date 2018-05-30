package com.bibi.crud.control;

import com.bibi.crud.bean.Department;
import com.bibi.crud.bean.Msg;
import com.bibi.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bibi on 2018/5/15.
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> depts = departmentService.getAll();

        return Msg.success().add("depts",depts);
    }

    @RequestMapping(value = "/dept/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getDept(@PathVariable("id") Integer deptId){
        Department dept = departmentService.getDept(deptId);

        return Msg.success().add("dept",dept);
    }

}
