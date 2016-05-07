package com.test.second.dao;

import java.sql.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Repository
public interface Dao { 
    public String select();
    public void insert(String id, String title, String start, String end);
}