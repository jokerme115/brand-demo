package com.HeTao.service;

import com.HeTao.mapper.BrandMapper;
import com.HeTao.pojo.Brand;
import com.HeTao.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    //调用BrandMapper.selectAll()
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 返回所有数据
     * @return Brand的集合
     */
    public List<Brand> selectAll()
    {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    public void add(Brand brand)
    {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    public Brand selectById(int id)
    {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    public void update(Brand brand)
    {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(int id)
    {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.delete(id);

        sqlSession.commit();
        sqlSession.close();
    }
}
