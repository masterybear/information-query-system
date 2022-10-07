package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.VirtualUserMapper;
import org.example.pojo.VirtualUser;
import org.example.service.VUserService;
import org.example.util.SqlSessionFactoryUtils;

public class VUserServiceImpl implements VUserService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public VirtualUser login(String userName, String password) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VirtualUserMapper mapper = sqlSession.getMapper(VirtualUserMapper.class);
        VirtualUser virtualUser = mapper.selectUsers(userName, password);

        sqlSession.close();

        return virtualUser;
    }

    public boolean register(VirtualUser user) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VirtualUserMapper mapper = sqlSession.getMapper(VirtualUserMapper.class);
        VirtualUser vu = mapper.selectName(user.getUserName());

        if (vu == null){
            mapper.addNew(user);
            sqlSession.commit();
        }

        sqlSession.close();
        return vu == null;
    }

    public boolean selectName(String userName) {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VirtualUserMapper mapper = sqlSession.getMapper(VirtualUserMapper.class);
        VirtualUser user = mapper.selectName(userName);

        return user != null;
    }
}
