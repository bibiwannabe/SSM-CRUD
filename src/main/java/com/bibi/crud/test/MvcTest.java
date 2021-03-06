package com.bibi.crud.test;

import com.bibi.crud.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.el.WebApplicationContextFacesELResolver;

import java.util.List;

/**
 * Created by bibi on 2018/5/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
@WebAppConfiguration//注解注入ioc容器
public class MvcTest {
    //传入SpringMVCioc
    @Autowired
    WebApplicationContext context;
    //虚拟MVC请求
    MockMvc mocMvc;

    @Before
    public void initMocMvc() {
        mocMvc =  MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
       MvcResult result =  mocMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","1")).andReturn();
       MockHttpServletRequest request =  result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页，"+pi.getPageNum());
        System.out.println("总页码"+pi.getPages());
        System.out.println("总记录数"+pi.getTotal());
        System.out.println("页面需要连续显示的页码");
        int[] nums = pi.getNavigatepageNums();
        for(int i :nums){
            System.out.println(" "+i);
        }
        List<Employee> list = pi.getList();
        for (Employee employee:list){
            System.out.println("ID:"+employee.getEmpId()+" 姓名："+employee.getEmpName()+"部门："+employee.getDepartment().getDeptName());
        }

    }
}
