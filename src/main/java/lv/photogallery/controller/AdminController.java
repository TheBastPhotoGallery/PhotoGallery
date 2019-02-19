package lv.photogallery.controller;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterRequest;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterResponse;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
public class AdminController {
    @Autowired
    private UserEnterService userEnterService;
    private Logger logger = LoggerFactory.getLogger(AdminController.class);
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView getlogin(){
//        ModelAndView modelAndView = new ModelAndView();
//        User user = new User();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/login")
//    public ModelAndView postLogin(String email, String password){
//        System.out.println("aasdadsasd");
//        //logger.info(user.getEmail());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//
//
//
//        UserEnterRequest userEnterRequest = new UserEnterRequest(email, password);
//        UserEnterResponse userEnterResponse = userEnterService.enter(userEnterRequest);
//
//        //modelAndView.addObject("password", "123" );
//        if(userEnterResponse.isSuccess()){
//            logger.info("Login success!");
//            modelAndView.setViewName("dashboard");
//        }
//        if(password != null && email != null) {
//            modelAndView.addObject("jumbo-title", "Error, please enter email");
//        }else{
//            modelAndView.addObject("jumbo-title", "");
//        }
//        // model.put("password","password");
//        return modelAndView;
//    }


    @RequestMapping("/login")
    public String login(String email, String password){
        UserEnterRequest userEnterRequest = new UserEnterRequest(email, password);
        UserEnterResponse userEnterResponse = userEnterService.enter(userEnterRequest);
        if(userEnterResponse.isSuccess()){
            logger.info("Login success!");
            return "dashboard";
        }

//        List<ValidationError> errorList = userEnterResponse.getErrors();
//        if (errorList.get(0).getField().equals("password")){
//            // Must be return of mustache
//        }else if (errorList.get(0).getErrorMessage().equals("Such email not found")){
//            // Must be return of mustache
//        }else{
//            // Must be return of mustache
//        }

        return "login";
    }
}
