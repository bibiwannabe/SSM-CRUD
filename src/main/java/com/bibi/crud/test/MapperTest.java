package com.bibi.crud.test;


import com.bibi.crud.bean.Department;
import com.bibi.crud.bean.Employee;
import com.bibi.crud.dao.DepartmentMapper;
import com.bibi.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by bibi on 2018/5/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void test(){
        /*
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        ioc.getBean(DepartmentMapper.class);
        */
        System.out.println(departmentMapper);
        Department record =  new Department(null,"开发部");
        departmentMapper.insertSelective(new Department(null,"运维部"));
        departmentMapper.insertSelective(new Department(null,"销售部"));
        departmentMapper.insertSelective(record);
    }
    @Test
    public void testEMP(){
        System.out.println(employeeMapper);
        employeeMapper.insertSelective(new Employee(null,"Kevin","男","123@qq.com",1));
        employeeMapper.insertSelective(new Employee(null,"Marry","女","234@qq.com",2));
        employeeMapper.insertSelective(new Employee(null,"Amy","女","234@qq.com",3));


    }

    @Test
    public void testMultiBuild(){
        EmployeeMapper mapper =  sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0; i<1000; i++){
            String uuid =  UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uuid,"男",uuid+"@qq.com",1));
        }
    }
}
