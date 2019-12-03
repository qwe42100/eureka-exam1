package com.lxl.service;

import com.github.pagehelper.PageInfo;
import com.lxl.pojo.Shop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface ShopService {
    /**
     * 列表
     * @param shop
     * @param pageNo
     * @param pageSize
     * @return
     */

    PageInfo<Shop> selectObject(@Param("shop") Shop shop, Integer pageNo, Integer pageSize);

    /**
     * 添加
     * @param shop
     * @return
     */
    int insert(Shop shop);

    /**
     * 修改
     * @param shop
     * @return
     */

    int update(Shop shop);

    /**
     * 查询单个的对象
     * @param id
     * @return
     */
    Shop getById(Integer id);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

}
