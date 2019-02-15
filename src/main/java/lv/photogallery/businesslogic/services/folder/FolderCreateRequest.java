package lv.photogallery.businesslogic.services.folder;

import lv.photogallery.businesslogic.builders.user.User;

public class FolderCreateRequest {
    private String folderName;
    private String file;
    private User user;

    public FolderCreateRequest(String folderName, String file, User user) {
        this.folderName = folderName;
        this.file = file;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
