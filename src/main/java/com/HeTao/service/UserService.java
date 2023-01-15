package com.HeTao.service;

import com.HeTao.mapper.UserMapper;
import com.HeTao.pojo.User;
import com.HeTao.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User SelectUser(String userName, String password)
    {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.select(userName, password);
    }

}
