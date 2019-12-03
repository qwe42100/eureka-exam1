package com.lxl.dao;

import com.lxl.pojo.Shop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopMapper {
    /**
     * 列表
     * @param shop
     * @param pageNo
     * @param pageSize
     * @return
     */

    List<Shop> selectObject(Shop shop,Integer pageNo,Integer pageSize);

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
    @Select("select * from shop where  id = #{id}")
    Shop getById(Integer id);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from shop where id = #{id}")
    int deleteById(Integer id);

}
