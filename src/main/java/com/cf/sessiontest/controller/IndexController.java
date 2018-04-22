package com.cf.sessiontest.controller;

import com.cf.sessiontest.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        Cookie cookies[]=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equalsIgnoreCase("JSESSIONID")){
                System.out.println("cookieid:"+cookie.getValue());
            }
        }
        System.out.println("sessionid:"+session.getId());
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

            User user = (User)session.getAttribute("user");
            if(user==null){
                User u = new User();
                u.setName("cf");
                u.setAge(29);
                session.setAttribute("user",u);
                System.out.println("user为空");
            }else{
                System.out.println("user不为空");
            }
        }


    }
}
