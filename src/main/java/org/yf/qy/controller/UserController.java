package org.yf.qy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.yf.qy.entity.User;
import org.yf.qy.service.UserService;
import org.yf.qy.vo.Response;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping(value = "/login")
    public Response login(@RequestBody User user, HttpSession session){
        User dbUser = userService.login(user);
        if(dbUser == null){
            return new Response(500);
        }
        session.setAttribute("userStore"+dbUser.getId(),dbUser);
        return new Response(200,dbUser);
    }

    @PostMapping(value = "/registry")
    public Response registry(@RequestBody User user){
        if(userService.findByName(user) != null){
            return new Response(405,"已存在");
        }
        if(userService.registry(user)){
            return new Response(500);
        }
        return new Response(200);
    }

    @GetMapping(value = "/all")
    public Response getAllUser(@RequestParam(value = "page", defaultValue = "1") int pageNo,
                               @RequestParam(value = "limit", defaultValue = "10") int pageSize){
        return userService.getAllUser(pageNo,pageSize);
    }
}
