package org.yf.qy.controller;


import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.yf.qy.service.QyService;
import org.yf.qy.service.UserQyService;
import org.yf.qy.vo.QyVo;
import org.yf.qy.vo.Response;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/api/qy")
public class QyController {

    @Autowired
    private QyService qyService;
    @Autowired
    private UserQyService userQyService;

    @PutMapping(value = "/update")
    public Response update()throws  Exception{
        qyService.update();
        return new Response(200);
    }

    @PutMapping(value = "/mark/{id}/{userId}")
    public Response mark(@PathVariable("id") long id, @PathVariable("userId") long userId){
        userQyService.mark(id,userId);
        return new Response(200);
    }

    @PutMapping(value = "/unmark/{id}/{userId}")
    public Response unmark(@PathVariable("id") long id, @PathVariable("userId") long userId){
        userQyService.unMark(id,userId);
        return new Response(200);
    }

    @PostMapping(value = "/query")
    public Response query(@RequestBody Map<String,Object> param){
        return userQyService.query(param);
    }

    @GetMapping(value = "/info")
    public Response info(){
        return new Response(200,userQyService.info());
    }

    @PutMapping(value = "/mark/all")
    public Response markAll(@RequestBody Map<String,Object> map){
        List<Long> ids = JSONArray.parseArray(map.get("ids").toString(),Long.class);
        Long userId = Long.valueOf(map.get("userId").toString());
        userQyService.markBatch(ids,userId);
        return new Response(200);
    }

    @PutMapping(value = "/unmark/all")
    public Response unMarkAll(@RequestBody Map<String,Object> map){
        List<Long> ids = JSONArray.parseArray(map.get("ids").toString(),Long.class);
        long userId = Long.parseLong(map.get("userId").toString());
        userQyService.unMarkBatch(ids,userId);
        return new Response(200);
    }
}
