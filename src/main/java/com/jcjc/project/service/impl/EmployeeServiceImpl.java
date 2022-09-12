package com.jcjc.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcjc.project.mapper.EmployeeMapper;
import com.jcjc.project.model.entity.Employee;
import com.jcjc.project.service.EmployeeService;

import org.springframework.stereotype.Service;

/**
* @author 神原秋人
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {
}






