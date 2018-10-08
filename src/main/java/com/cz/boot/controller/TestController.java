package com.cz.boot.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018-09-29.
 */
@RestController
@RequestMapping("")
public class TestController {
    //http://localhost:8086/swagger-ui.html
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //测试1
    @ApiOperation(value="打印信息", notes="根据url的str来打印相关信息")
    @ApiImplicitParam(name = "str", value = "str", required = true, dataType = "String")
    @RequestMapping(value="/hello",method=RequestMethod.POST)
    public Object getHelloStr(@RequestParam(value="str") String str){
        logger.info("参数str的值是"+str);
        return  "hello "+str;
    }

    //测试2
    @ApiOperation(value="打印信息1", notes="根据url的str1来打印相关信息")
    @ApiImplicitParam(name = "str1", value = "str1", required = true, dataType = "String")
    @RequestMapping(value="/hello1/{str1}", method=RequestMethod.GET)
    public Object getHelloStr1(@PathVariable(value="str1") String str1){
        logger.warn("参数str1必须输入");
        return  "hello world"+str1;
    }
}
