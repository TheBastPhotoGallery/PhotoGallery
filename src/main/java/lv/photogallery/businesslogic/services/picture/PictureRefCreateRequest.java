package lv.photogallery.businesslogic.services.picture;

import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.user.User;

public class PictureRefCreateRequest {
    private Folder folder;
    private String picturePath;
    private User user;

    public PictureRefCreateRequest(Folder folder, String pictureName, User user) {
        this.folder = folder;
        this.picturePath = pictureName;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
