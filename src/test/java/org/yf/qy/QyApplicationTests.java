package org.yf.qy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yf.qy.service.QyService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QyApplication.class})
public class QyApplicationTests {

    @Autowired
    private QyService qyServiceImpl;

    @Test
    public void fun1() throws Exception{
        qyServiceImpl.update();
        System.out.println("=====================>over");
    }

}
