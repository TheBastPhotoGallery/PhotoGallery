package lv.photogallery.controller;

import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.photoservicereservation.PhotoServiceReservationService;
import lv.photogallery.businesslogic.services.user.userenter.UserEnterService;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/myphotos")
    public String photos(@RequestParam Integer usrId, @RequestParam Long albumId, Map<String, Object> model) {
        Collection<Folder> folders;
        if(usrId.equals(userRepo.findByEmail("photogallerybootcamp@gmail.com").get().getId().intValue())){
            folders = folderRepo.findAll();
        }else{
            folders = folderRepo.findByUsrId(usrId);
        }
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
        model.put("folderId", albumId);
        return "myphotos";
    }


    @PutMapping("/myphotos")
    public String add(@RequestParam String markForEdit, @RequestParam String all, @RequestParam String userIdd, @RequestParam String folderIdd){

        List<String> pictureListStringTrue = new ArrayList<String>(Arrays.asList(markForEdit.split(",")));
        List<String> pictureListStringAll = new ArrayList<String>(Arrays.asList(all.split(",")));
        List<Long> pictureListLongAll = new ArrayList<Long>();
        List<Long> pictureListLongTrue = new ArrayList<Long>();

        for(String a:pictureListStringAll){
            pictureListLongAll.add(Long.valueOf(a));
        }

        for(String a:pictureListStringTrue){
            pictureListLongTrue.add(Long.valueOf(a));
        }

        Collection<Picture> picCollection = pictureRepo.findByIdIn(pictureListLongAll);

        for(Picture p : picCollection){

            if(pictureListLongTrue.contains(p.getId())){
                p.setCheckBox(1);
            }
            else{
                p.setCheckBox(0);
            }
        }
        pictureRepo.saveAll(picCollection);
        return "redirect:/myphotos?usrId="+userIdd+"&albumId="+folderIdd;
    }

@RequestMapping("/dashboard2")
public String dashboard2() {

    return "dashboard2";
}
}

