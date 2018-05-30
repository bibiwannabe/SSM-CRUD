package com.bibi.crud.service;

import com.bibi.crud.bean.Employee;
import com.bibi.crud.bean.EmployeeExample;
import com.bibi.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bibi on 2018/5/14.
 */

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /*
    查询所有员工
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count =  employeeMapper.countByExample(example);
        return count==0;
    }

    public Employee getEmp(Integer empId) {
        Employee employee =  employeeMapper.selectByPrimaryKey(empId);
        return employee;

    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }


    public void deleteEmp(Integer empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }

    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);

    }
}
