package lv.photogallery.controller;

import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationRequest;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationResponse;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import java.util.Collection;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private FolderRepository folderRepo;
    @Autowired
    private PictureRepository pictureRepo;
    @Autowired
    private UserRepository userRepo;


    @Autowired
    private UserRegistrationService userRegistrationService;

    @RequestMapping("/")
    public String index() {

        return "index";
    }
    @RequestMapping("/about")
    public String about(){
        return "about";

    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {

        return "dashboard";
    }

    @RequestMapping("/admin")
    public String admin() {

        return "admin";
    }
//    public String photos(@RequestParam Integer usrId, @RequestParam(value = "albumId", defaultValue = "null", required = false), Map<String, Object> model) {
//
    @RequestMapping("/myphotos")
    public String photos(@RequestParam Integer usrId, @RequestParam Long albumId, Map<String, Object> model) {
        Collection<Folder> folders = folderRepo.findByUsrId(usrId);
        Boolean albumAccess = false;
        for(Folder folder: folders) {
            if(folder.getId().equals(albumId)){
                albumAccess = true;
            }
        }
        Collection<Picture> pictures = pictureRepo.findByFolderId(0L) ;
        if(albumAccess == true) {
            pictures = pictureRepo.findByFolderId(albumId);
        }



        model.put("folders", folders);
        model.put("pictures", pictures);
        model.put("usrId", usrId);
        return "myphotos";
    }
    //



    @RequestMapping("/registration")
    @ResponseBody
    public String registration(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password) {


        User user = new User();
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest(user.getEmail(), user.getPassword());
        UserRegistrationResponse registrationResponse = userRegistrationService.register(registrationRequest);

        if (registrationResponse.isSuccess()) {
            return "dashboard";
        }
        if (registrationResponse.isSuccess() && email == "admin@admin.com") {
            return "admin";
        } else {
            return "registration";
        }

    }
}

