package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.BrandMapper;
import org.example.pojo.Brand;
import org.example.pojo.PageBean;
import org.example.service.BrandService;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Brand> selectAll() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = mapper.selectAll();

        sqlSession.close();

        return brands;
    }

    //新增信息
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);

        sqlSession.commit();
        sqlSession.close();
    }

    public void delByBrandId(int[] ids){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.delByBrandId(ids);

        sqlSession.commit();
        sqlSession.close();

    }

    //分页查询（包含条件）
    public PageBean<Brand> selectPageAndCondition(int currentPage, int pageSize,Brand brand) {

        //创建session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得映射对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //从begin行查起，返回size条数据
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        //设置模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%"+companyName+"%");
        }
        //执行查询
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size,brand);

        //需要查询的总行数
        int totalCount = mapper.selectTotalCount(brand);

        //封装到pageBean对象
        PageBean<Brand> pageBean = new PageBean<Brand>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //关闭资源
        sqlSession.close();

        return pageBean;
    }

    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);

        sqlSession.commit();
        sqlSession.close();
    }
}
