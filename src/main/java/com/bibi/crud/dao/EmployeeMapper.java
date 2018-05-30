package com.bibi.crud.dao;

import com.bibi.crud.bean.Employee;
import com.bibi.crud.bean.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    List<Employee> selectByExampleWithDept(EmployeeExample example);

    Employee selectByPromaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    void updateByPrimaryKeySelective(Employee employee);

    void deleteByPrimaryKey(Integer empId);
}