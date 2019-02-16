package lv.photogallery.businesslogic.services.picture;

import lv.photogallery.businesslogic.builders.user.User;

public class PictureRefCreateRequest {
    private String folderName;
    private String picturePath;
    private User user;

    public PictureRefCreateRequest(String folderName, String pictureName, User user) {
        this.folderName = folderName;
        this.picturePath = pictureName;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
