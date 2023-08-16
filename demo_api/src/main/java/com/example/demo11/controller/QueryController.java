package com.example.demo11.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QueryController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = "*")
    @GetMapping("/ScrapPitList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from camera_setting";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
    @GetMapping("/ScrapSteelStorage")
    public List<Map<String, Object>> ScrapSteelStorage( String keyWorks) {
        if (null==keyWorks ||keyWorks.equals("xxxx")){
            keyWorks="2023-07-05";
        }else if (keyWorks.equals("")){
            String sql1 = "select * from sys_judge_detail order by create_time desc ";
            List<Map<String, Object>> result1 = jdbcTemplate.queryForList(sql1);
            return result1;
        }
        String str1 = "check_number ='"+keyWorks+"'";
        String str2 = "project_plan_name ='"+keyWorks+"'";
        String str3 = "low_value_per ='"+keyWorks+"'";
        String str4 = "create_time like '%"+keyWorks+"%'";
        String str5 = "license_plate = '"+keyWorks+"'";
        String str6 = "create_name = '"+keyWorks+"'";

        String sql1 = "select * from sys_judge_detail where "+str1+" order by create_time desc ";
        String sql2 = "select * from sys_judge_detail where "+str2+" order by create_time desc ";
        String sql3 = "select * from sys_judge_detail where "+str3+" order by create_time desc ";
        String sql4 = "select * from sys_judge_detail where "+str4+" order by create_time desc ";
        String sql5 = "select * from sys_judge_detail where "+str5+" order by create_time desc ";
        String sql6 = "select * from sys_judge_detail where "+str6+" order by create_time desc ";
        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
        System.out.println(sql4);
        System.out.println(sql5);
        System.out.println(sql6);
        List<Map<String, Object>> result1 = jdbcTemplate.queryForList(sql1);
        List<Map<String, Object>> result2 = jdbcTemplate.queryForList(sql2);
        List<Map<String, Object>> result3 = jdbcTemplate.queryForList(sql3);
        List<Map<String, Object>> result4 = jdbcTemplate.queryForList(sql4);
        List<Map<String, Object>> result5 = jdbcTemplate.queryForList(sql5);
        List<Map<String, Object>> result6 = jdbcTemplate.queryForList(sql6);
        //拼接多个list
        result1.addAll(result2);
        result1.addAll(result3);
        result1.addAll(result4);
        result1.addAll(result5);
        result1.addAll(result6);
        //现在的时间,格式化为yyyy-MM-dd HH:mm:ss形式
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        System.out.println(result1.size());
        return result1;

    }

}
