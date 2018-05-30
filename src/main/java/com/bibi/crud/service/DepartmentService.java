package com.bibi.crud.service;

import com.bibi.crud.bean.Department;
import com.bibi.crud.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bibi on 2018/5/15.
 */

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    /*
    查询所有员工
     */
    public List<Department> getAll() {
        return departmentMapper.selectByExample(null);
    }

    public Department getDept(Integer deptId) {
        return departmentMapper.selectByPrimaryKey(deptId);
    }
}
