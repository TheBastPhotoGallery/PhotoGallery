package lv.photogallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {
//    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
//    public ModelAndView getListFromFolder(ModelMap map){
//        System.out.println("123");
//        ModelAndView modelAndView = new ModelAndView();
////        for (String s : listing){
////            System.out.println(s);
////        }
//modelAndView.setViewName("dashboard");
//
//        return modelAndView;
//    }

    @RequestMapping(value = "/dashboard1", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@RequestParam("filelist") String[] list) {

        System.out.println("123");
//
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();

                // Creating the directory to store file
//                String rootPath = System.getProperty("catalina.home");
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if (!dir.exists())
//                    dir.mkdirs();

                // Create the file on server
//                File serverFile = new File(dir.getAbsolutePath()
//                        + File.separator + name);
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(serverFile));
//                stream.write(bytes);
//                stream.close();
//
//                logger.info("Server File Location="
//                        + serverFile.getAbsolutePath());

//                return "You successfully uploaded file=" + name;
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name;
//        }
return "dashboard";
    }
}
