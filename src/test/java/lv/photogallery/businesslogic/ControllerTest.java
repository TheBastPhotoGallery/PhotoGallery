package lv.photogallery.businesslogic;

import lv.photogallery.controller.LoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ControllerTest {
    @Test
    public void loginControllerTest(){
        LoginController loginController = new LoginController();
        //ModelAndView modelAndView = loginController.handleRequest();
    }
}
