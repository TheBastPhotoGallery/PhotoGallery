package lv.photogallery.controller;

import lv.photogallery.businesslogic.builders.folder.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.database.FolderRepository;


import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private FolderRepository folderRepo;

    @RequestMapping("/")
    public String index() {

        return "index";
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/registration")
    public String registration() {

        return "registration";
    }
    @RequestMapping("/gallery")
    public String gallery() {

        return "gallery";
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
    public String photos(@RequestParam Integer usrId, Map<String, Object> model) {
        Iterable<Folder> folders= folderRepo.findByUsrId(usrId);

        model.put("folders", folders);
        return "myphotos";
    }

}