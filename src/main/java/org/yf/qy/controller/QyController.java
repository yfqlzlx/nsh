package org.yf.qy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.yf.qy.service.QyService;
import org.yf.qy.vo.Response;

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

    @PutMapping(value = "/update")
    public Response update()throws  Exception{
        qyService.update();
        return new Response(200);
    }
}
