package com.jcjc.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jcjc.project.mapper.AddressBookMapper;
import com.jcjc.project.model.entity.AddressBook;

import com.jcjc.project.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
* @author 神原秋人
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2022-09-07 17:12:32
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService {

}




