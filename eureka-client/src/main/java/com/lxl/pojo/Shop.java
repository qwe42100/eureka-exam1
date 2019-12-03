package com.lxl.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.security.PrivateKey;
import java.util.Date;

public class Shop {
    //主键
     private Integer id;
    //商品名称
     private String name;
    //价格
    private Integer price;
    //图片路径
    private String imge_url;
    //上架状态
    private Integer upper_status;
   // 出厂日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date factory_date;
    //数量
    private Integer num;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImge_url() {
        return imge_url;
    }

    public void setImge_url(String imge_url) {
        this.imge_url = imge_url;
    }

    public Integer getUpper_status() {
        return upper_status;
    }

    public void setUpper_status(Integer upper_status) {
        this.upper_status = upper_status;
    }

    public Date getFactory_date() {
        return factory_date;
    }

    public void setFactory_date(Date factory_date) {
        this.factory_date = factory_date;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imge_url='" + imge_url + '\'' +
                ", upper_status=" + upper_status +
                ", factory_date=" + factory_date +
                ", num=" + num +
                '}';
    }

    public Shop() {
    }

    public Shop(Integer id, String name, Integer price, String imge_url, Integer upper_status, Date factory_date, Integer num) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imge_url = imge_url;
        this.upper_status = upper_status;
        this.factory_date = factory_date;
        this.num = num;
    }
}
