package com.test.second.dao;

import java.sql.*;
import java.util.*;

import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.test.second.object.*;

@Repository
public interface Dao { 
    public ArrayList<CalendarObj> select(String user_id);
    public void insert(String id, String schedule_id, String title, String start, String end);
    public void delete(String id, String schedule_id, String title, String start, String end);
}