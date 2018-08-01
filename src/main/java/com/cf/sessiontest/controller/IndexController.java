package com.cf.sessiontest.controller;

import com.cf.sessiontest.model.User;
import com.cf.sessiontest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private IUserService userService;

    @RequestMapping("mvc")
    @ResponseBody
    public String mvc(String mes){
        return "mvc:"+mes;
    }

    @RequestMapping("index")
    public String index(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookies[]=request.getCookies();
//        int i=1/0;
        User user = new User();
        user.setName("陈");
        user.setAge(29);
        String re = userService.testTransaction();
        System.out.println("调用结果："+re);
//        userService.save(user);
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equalsIgnoreCase("JSESSIONID")){
//                    System.out.println("cookieid:"+cookie.getValue());
                }
            }
        }
//        System.out.println("sessionid:"+session.getId());
        return "index";
    }

    @RequestMapping("add")
    public void add(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(null==cookies) {
            System.out.println("没有cookie==============");
        }else{
            Cookie cookie = new Cookie("loginid", session.getId());
            cookie.setMaxAge(30* 60);// 设置为30min
            cookie.setPath("/");
            System.out.println("已添加===============");
            response.addCookie(cookie);
        }
    }
}
