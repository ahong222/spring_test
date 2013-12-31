package com.test;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HelloWorldController { 
    @RequestMapping("/hello.do")
    public ModelAndView helloWorld() { 
        System.out.println("HelloWorldController");
        String message = "Hello World, Spring 3.0!";
        return new ModelAndView( "hello",  "message", message);
    }
    
    @RequestMapping(value="/welcome.do",method=RequestMethod.GET)
    public void welcome(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("welcome");
        String name=request.getParameter("userName");
        String pwd=request.getParameter("pwd");
        
        String message = null;
        try {
            JSONObject content=new JSONObject();
            content.put("name", name);
            content.put("pwd", pwd);
            System.out.println(content.toString());
            message=content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.getOutputStream().write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return new ModelAndView("json","message",message);
    }
    
    @RequestMapping(value = "/hello2.do")  
    public String hello2(int id,Model model){  
        System.out.println("hello2 action:"+id);  
        model.addAttribute("name", "huangjie");  
        //这个只有值没有键的情况下,使用Object的类型作为key,String-->string  
        model.addAttribute("ok");
        return "hello";  
    }  
  
    //得到request,response,session等,只要在方法形参中声明参数即可  
    @RequestMapping(value = "/hello3.do")  
    public String hello3(HttpServletRequest request){  
        String id = request.getParameter("id");  
        System.out.println("hello3 action:"+id);  
        return "hello";  
    }  
}