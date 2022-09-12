package com.jcjc.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jcjc.project.mapper.UserMapper;
import com.jcjc.project.model.entity.User;

import com.jcjc.project.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author 神原秋人
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




