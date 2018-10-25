package com.cz.boot.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zc on 2018-10-24.
 */
public interface EmpService {
    /**
     * 查询所有的员工
     * @param jsonObj
     * @return
     */
    public Object getEmp( JSONObject jsonObj);

    /**
     * 查询员工信息根据员工姓名
     * @param jsonObj
     * @return
     */
    public Object getEmpById( JSONObject jsonObj);

    /**
     * 增加/更新员工信息
     * @param jsonObj
     * @return
     */
    public Object addEmp( JSONObject jsonObj);

    /**
     * 删除员工
     * @param jsonObj
     * @return
     */
    public Object delEmpById( JSONObject jsonObj);
}
