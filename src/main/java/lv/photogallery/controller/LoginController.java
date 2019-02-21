package lv.photogallery.controller;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterRequest;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterResponse;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class LoginController {
    @Autowired
    private UserEnterService userEnterService;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ModelAndView postLogin(String email, String password) {
        ModelAndView modelAndView = new ModelAndView();
        if (password == null && email == null) {
            modelAndView.addObject("jumbo", "");
        } else {
            UserEnterRequest userEnterRequest = new UserEnterRequest(email, password);
            UserEnterResponse userEnterResponse = userEnterService.enter(userEnterRequest);
            modelAndView.setViewName("login");

            if (userEnterResponse.isSuccess()) {
                logger.info("Login success!");

                modelAndView.setViewName("redirect:/myphotos?usrId=" + userEnterResponse.getUserId().intValue() +"&albumId=" +userEnterResponse.getUserId().intValue());
                return  modelAndView;
            } else {
                modelAndView = errorMsg(modelAndView, userEnterResponse.getErrors());
            }
        }
        return modelAndView;
    }

    public ModelAndView errorMsg(ModelAndView modelAndView, List<ValidationError> list) {
        if (list.get(0).getField().equals("email")) {
            if (list.get(0).getErrorMessage().equals("Must not be empty")) {
                modelAndView.addObject("jumbo", "Email must not be empty");
            } else{
                modelAndView.addObject("jumbo", "Such email not found");
            }
        }else if (list.get(0).getField().equals("password")) {
            if (list.get(0).getErrorMessage().equals("Must not be empty")) {
                modelAndView.addObject("jumbo", "Incorrect password field");
            } else {
                modelAndView.addObject("jumbo", "Incorrect password");
            }
        }
        return modelAndView;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView getlogin(){
//        ModelAndView modelAndView = new ModelAndView();
//        User user = new User();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
//
}
