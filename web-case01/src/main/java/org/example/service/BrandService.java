package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.Brand;
import org.example.pojo.PageBean;

import java.util.List;

public interface BrandService {

    List<Brand> selectAll();

    void add(Brand brand);

    void update(Brand brand);

    void delByBrandId(int[] ids);

    PageBean<Brand> selectPageAndCondition(int currentPage, int pageSize, Brand brand);
}
