package com.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 描述:处理异常的控制器
 *
 * @Author shilei
 * @Date 2019/1/15
 */
@ControllerAdvice
public class ExecptionController {

    // 这里处理所有代码异常
    @ExceptionHandler(value = {Exception.class})
    public String handleException1(Exception excep, Model model){
        model.addAttribute("errmsg", "服务器出现问题，当前页面无法访问，请访问其它页面!");
        // 可以自行实现error.jsp错误页面
        excep.printStackTrace();
        return "error";
    }

    /*@ExceptionHandler(value = {ArrayIndexOutOfBoundsException.class})
    public String handleException2(ArrayIndexOutOfBoundsException excep){
        return "error";
    }

    @ExceptionHandler(value = {IOException.class})
    public String handleException3(IOException excep){
        return "error";
    }*/
}
