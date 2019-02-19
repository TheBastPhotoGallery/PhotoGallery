package lv.photogallery.controller;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.email.SendEmail;
import lv.photogallery.businesslogic.photoservicereservation.PhotoServiceReservationRequest;
import lv.photogallery.businesslogic.photoservicereservation.PhotoServiceReservationResponse;
import lv.photogallery.businesslogic.photoservicereservation.PhotoServiceReservationService;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationRequest;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationResponse;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
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
    private PhotoServiceReservationService service;


    @Autowired
    private UserRegistrationService userRegistrationService;


    @RequestMapping("/")
    public String index(@RequestParam(value = "photo", required = false) String photo, @RequestParam(value = "time", required = false) String time, @RequestParam(value = "email", required = false) String email) {

        if ((email!= null)) {
            PhotoServiceReservationRequest request = new PhotoServiceReservationRequest(photo, time, email);
            PhotoServiceReservationResponse response = service.reserve(request);
            if (response.isSuccess()) {
                return "login";
            } else {
                List<ValidationError> errors = response.getErrors();
                if ((errors.get(0).getField() == "service") && (errors.get(0).getErrorMessage() == "This field must be completed!")) {
                    return "about";
                }
//                if ((errors.get(0).getField() == "dateTime") && (errors.get(0).getErrorMessage() == "Sorry, your desired time is booked!")) {
//                    return "dashboard";
//                }
//                if ((errors.get(0).getField() == "email") && (errors.get(0).getErrorMessage() == "Email error!")) {
//                    return "dashboard";
//                }
                if ((errors.get(0).getField() == "email") && (errors.get(0).getErrorMessage() == "You are not registered!")) {
                    return "dashboard";
                }
            }
        }
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

    @RequestMapping("/registration")
//  public String registration(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, @RequestParam(value = "repeat") String repeat) {
        public String registration(String email, String password, String repeat) {
        if (email!= null){
           if (password.trim().equals(repeat.trim())) {
               UserRegistrationRequest request = new UserRegistrationRequest(email, password);
               UserRegistrationResponse response = userRegistrationService.register(request);
               if (response.isSuccess()) {
                   return "login";
               } else {
                   List<ValidationError> errors = response.getErrors();
                   StringBuilder sb = new StringBuilder();
                   sb.append(email);
                   sb.append(errors.get(0).getField());
                   sb.append(errors.get(0).getErrorMessage());
                   SendEmail.SendMailMessage(sb, "ajup@inbox.lv");

                   if ((errors.get(0).getField().equals("email")) && (errors.get(0).getErrorMessage().equals("Must not be empty"))) {
                       return "about";
                   }
               }
           }
            else {
               StringBuilder sb = new StringBuilder();
               sb.append(email);
               sb.append(password);
               sb.append(repeat);
               SendEmail.SendMailMessage(sb, "ajup@inbox.lv");
               return "login";
           }
        }
        return "/registration";
    }
}

