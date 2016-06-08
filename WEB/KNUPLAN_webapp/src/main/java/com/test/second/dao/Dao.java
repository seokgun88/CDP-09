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
    public void insert(String id, String title, String start, String end);
    public void delete(String id, String title, String start, String end);
    public void update(String id, String title, String cstart, String cend, String start, String end); //cstart, cend : 변경전 시간
    public void class_insert(String building , String room ,int day ,int start, int end, String schedule);
    public ArrayList<ClassroomScheduleObj> class_select(String building, String room);
    public ArrayList<ClassroomScheduleObj> building_select(String building, int day, int startTime, int endTime);
    public ArrayList<ClassroomScheduleObj> room_select(String building);
}