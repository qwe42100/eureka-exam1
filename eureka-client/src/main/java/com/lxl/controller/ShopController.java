package com.lxl.controller;

import com.lxl.pojo.Shop;
import com.lxl.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("shop")
public class ShopController {
    @Value("${file.path}")
    private String filePath;
    @Value("${file.domain}")
    private String fileDomain;


    @Autowired
    private ShopService shopService;

    /**
     * 列表和分页
     * @param shop
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("list")
    public Object list(Shop shop, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "3")Integer pageSize){
        return shopService.selectObject(shop,pageNo,pageSize);
    }

    /**
     * 添加
     * @param shop
     * @return
     */
    @RequestMapping("save")
    public Object save(@RequestBody Shop shop){
        return shopService.insert(shop);
    }

    /**
     * 修改
     * @param shop
     * @return
     */

    @RequestMapping("update")
    public Object update(Shop shop){
        return shopService.update(shop);
    }

    /**
     * 查询单个对象
     * @param id
     * @return
     */
    @RequestMapping("getById")
    public Object getById(Integer id){
        return shopService.getById(id);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("delById")
    public Object delById(Integer id){
        return shopService.deleteById(id);
    }


    /**
     * 图片上传
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("upload")
    public Object upload(@RequestParam ("file")MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String absFileName = filePath+fileName;
        File file1 = new File(absFileName);
        file.transferTo(file1);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("hashMap",true);
        hashMap.put("fileName",fileName);
        hashMap.put("fileUrl",fileDomain+fileName);
        return hashMap;
    }














}
