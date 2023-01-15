package com.HeTao.service;

import com.HeTao.mapper.UserMapper;
import com.HeTao.pojo.User;
import com.HeTao.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录方法
     * @param userName 用户名
     * @param password 密码
     * @return 返回对象
     */
    public User SelectUser(String userName, String password)
    {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.select(userName, password);
    }


    public boolean register(User user)
    {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = mapper.selectByUsername(user.getUsername());

        if (user1 == null)
        {
            //用户不存在,注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return user1 == null;
    }
}
