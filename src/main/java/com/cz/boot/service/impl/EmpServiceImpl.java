package com.cz.boot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cz.boot.dao.impl.CommonDao;
import com.cz.boot.pojo.Emp;
import com.cz.boot.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zc on 2018-10-24.
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public Object getEmp(JSONObject jsonObj) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try{
            int pageno = Integer.parseInt(jsonObj.get("pageno").toString());
            int pagesize = Integer.parseInt(jsonObj.get("pagesize").toString());

            List<Map> list  = commonDao.findListPageSQL("select * from emp ",pageno,pagesize);
            int rows  = Integer.parseInt(commonDao.findBySql("select count(1) as cnt from emp ").get("cnt").toString());
            resultMap.put("data",list);
            resultMap.put("rows",rows);
            resultMap.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("data","");
            resultMap.put("rows","");
            resultMap.put("code",-200);
        }
        return resultMap;
    }

    @Override
    public Object getEmpById(JSONObject jsonObj) {
        Object obj = null;
        try{
            Map <String,Object> map = new HashMap<String,Object>();
            long empno = Long.parseLong(jsonObj.get("empno").toString());
            map.put("empno",empno);
            Emp emp = (Emp)commonDao.findByJpql("From Emp where empno = :empno",map);
            obj = emp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Object addEmp(JSONObject jsonObj) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try{
            Emp emp = JSON.parseObject(jsonObj.toString(), Emp.class);
            commonDao.save(emp);
            resultMap.put("msg","保存成功");
            resultMap.put("code",200);
        }catch(Exception e){
            e.printStackTrace();
            resultMap.put("msg","保存失败"+e.getMessage());
            resultMap.put("code",-200);
        }
        return resultMap;
    }

    @Override
    public Object delEmpById(JSONObject jsonObj) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try{
            long empno = Long.parseLong(jsonObj.get("empno").toString());
            commonDao.deleteById(empno);
            resultMap.put("msg","删除成功");
            resultMap.put("code",200);
        }catch(Exception e){
            e.printStackTrace();
            resultMap.put("msg","删除失败"+e.getMessage());
            resultMap.put("code",-200);
        }
        return resultMap;
    }
}
