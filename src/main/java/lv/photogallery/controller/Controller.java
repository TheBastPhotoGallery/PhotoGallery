package lv.photogallery.controller;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.photoservicereservation.PhotoServiceReservationRequest;
import lv.photogallery.businesslogic.photoservicereservation.PhotoServiceReservationResponse;
import lv.photogallery.businesslogic.photoservicereservation.PhotoServiceReservationService;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterRequest;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterResponse;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterService;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationRequest;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationResponse;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private UserEnterService userEnterService;


    @RequestMapping("/")
 //   public String index(@RequestParam(value = "photo", required = false) String photo, @RequestParam(value = "time", required = false) String time, @RequestParam(value = "email", required = false) String email) {
    public String index(String weddings, String kids, String other, String time, String email) {
        if ((email!= null) && (!email.isEmpty())) {
            Optional<User> userOpt = userRepo.findByEmail(email);
            if (!userOpt.isPresent()) {
                return "registration7";

            }

            String photo = null;
            if (weddings != null) {
                photo = weddings;
            } else {
                if (kids != null) {
                    photo = kids;
                } else {
                    photo = other;
                }
            }
            PhotoServiceReservationRequest request = new PhotoServiceReservationRequest(photo, time, email);
            PhotoServiceReservationResponse response = service.reserve(request);
            if (response.isSuccess()) {
                return "index6";
            } else {
                List<ValidationError> errors = response.getErrors();
                if ((errors.get(0).getField().equals("service")) && (errors.get(0).getErrorMessage().equals("This field must be completed!"))) {
                    return "index3";
                }
                if ((errors.get(0).getField().equals("dateTime")) && (errors.get(0).getErrorMessage().equals("This field must be completed!"))) {
                    return "index4";
                }
                if ((errors.get(0).getField().equals("dateTime")) && (errors.get(0).getErrorMessage().equals("Date/Time format error!"))) {
                    return "index4";
                }
                if ((errors.get(0).getField().equals("dateTime")) && (errors.get(0).getErrorMessage().equals("Input error!"))) {
                    return "index4";
                }
                if ((errors.get(0).getField().equals("dateTime")) && (errors.get(0).getErrorMessage().equals("Sorry, your desired time is booked!"))) {
                    return "index5";
                }
            }
        }return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {

        return "dashboard";
    }

    @RequestMapping("/admin")
    public String admin() {

        return "admin";
    }

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

//    @RequestMapping("/registration")
//    public String registration(String email, String password, String repeat) {
//        if (email!= null){
//           if (password.equals(repeat)) {
//               UserRegistrationRequest request = new UserRegistrationRequest(email, password);
//               UserRegistrationResponse response = userRegistrationService.register(request);
//               if (response.isSuccess()) {
//                   return "index2";
//               } else {
//                   List<ValidationError> errors = response.getErrors();
//                   if ((errors.get(0).getField().equals("email")) && (errors.get(0).getErrorMessage().equals("Must not be empty"))) {
//                       return "registration2";
//                   }
//                   if ((errors.get(0).getField().equals("password")) && (errors.get(0).getErrorMessage().equals("Must not be empty"))) {
//                       return "registration3";
//                   }
//                   if ((errors.get(0).getField().equals("email")) && (errors.get(0).getErrorMessage().equals("Must not be repeated"))) {
//                       return "registration4";
//                   }
//                   if ((errors.get(0).getField().equals("email")) && (errors.get(0).getErrorMessage().equals("Email error!"))) {
//                       return "registration6";
//                   }
//               }
//           }
//           else {
//               return "registration5";
//           }
//        }
//        return "registration";
//    }
}

