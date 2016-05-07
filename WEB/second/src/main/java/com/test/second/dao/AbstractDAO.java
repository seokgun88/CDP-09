package com.test.second.dao;

import java.sql.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Repository
public class AbstractDAO {
	@Autowired
    private SqlSession query;
 
    public void calendar() throws SQLException {
        System.out.println(query.selectList("query.select"));
    }
}