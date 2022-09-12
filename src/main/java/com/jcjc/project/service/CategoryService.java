package com.jcjc.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcjc.project.model.entity.Category;
import org.springframework.stereotype.Service;


/**
* @author 神原秋人
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-09-07 17:12:32
*/

public interface CategoryService extends IService<Category> {

    public void remove(Long id);

}
