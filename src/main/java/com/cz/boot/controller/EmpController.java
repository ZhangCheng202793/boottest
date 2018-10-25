package com.cz.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.cz.boot.service.EmpService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zc on 2018-10-24.
 */
@RestController
@RequestMapping(value="/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @ApiOperation(value="查询所有的员工")
    @ApiImplicitParam(name = "jsonObj", paramType = "body",examples = @Example({
            @ExampleProperty(value = "{'pageno':'pageno','pagesize':'pagesize'}", mediaType = "application/json")
    }))
    @PostMapping("/getEmp")
    public Object getEmp(@RequestBody JSONObject jsonObj){
        return empService.getEmp(jsonObj);
    }

    @ApiOperation(value="查询员工工号根据员工姓名")
    @ApiImplicitParam(name = "jsonObj", value = "jsonObj", required = true)
    @PostMapping("/getEmpById")
    public Object getEmpById(@RequestBody JSONObject jsonObj){
        return empService.getEmpById(jsonObj);
    }

    @ApiOperation(value="增加/更新员工信息")
    @ApiImplicitParam(name = "jsonObj", value = "jsonObj", required = true)
    @PostMapping("/addEmp")
    public Object addEmp(@RequestBody JSONObject jsonObj){
        return empService.addEmp(jsonObj);
    }

    @ApiOperation(value="删除员工")
    @ApiImplicitParam(name = "jsonObj", value = "jsonObj", required = true)
    @PostMapping("/delEmpById")
    public Object delEmpById(@RequestBody JSONObject jsonObj){
        return empService.delEmpById(jsonObj);
    }
}
