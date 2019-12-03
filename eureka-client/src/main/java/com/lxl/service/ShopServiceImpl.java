package com.lxl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxl.dao.ShopMapper;
import com.lxl.pojo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    private ShopMapper shopMapper;
    @Override
    public PageInfo<Shop> selectObject(Shop shop, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Shop> list = shopMapper.selectObject(shop, pageNo, pageSize);
        return new PageInfo<>(list);
    }

    @Override
    public int insert(Shop shop) {
        if (shop.getId()==null){
          return  shopMapper.insert(shop);
        }
        return shopMapper.update(shop);
    }

    @Override
    public int update(Shop shop) {
        return shopMapper.update(shop);
    }

    @Override
    public Shop getById(Integer id) {
        return shopMapper.getById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return shopMapper.deleteById(id);
    }
}
