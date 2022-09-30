package com.jcjc.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcjc.project.dto.SetmealDto;
import com.jcjc.project.model.entity.Setmeal;

import java.util.List;


/**
* @author 神原秋人
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2022-09-07 17:12:32
*/
public interface SetmealService extends IService<Setmeal> {


    /**
     * 新增套餐，同时保存套餐和菜品的关联
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);


    public void removeWithDish(List<Long> ids);

    /**
     * 根据套餐id修改售卖状态
     * @param status
     * @param ids
     */
    void updateSetmealStatusById(Integer status,List<Long> ids);

    /**
     * 回显套餐数据：根据套餐id查询套餐
     * @param id
     * @return
     */
    SetmealDto getDate(Long id);

}
