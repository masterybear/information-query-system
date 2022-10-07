package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Brand;

import java.util.List;

public interface BrandMapper {

    //查询
    @Select("select * from tb_brand")
    @ResultMap("brandMap")
    List<Brand> selectAll();

    //插入
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //批量删除
    void delByBrandId(@Param("ids") int[] ids);

    //分页查询
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size,@Param("brand") Brand brand);

    //查询总记录数
    int selectTotalCount(Brand brand);

    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status= #{status} where id = #{brandId}")
    void update(Brand brand);
}
