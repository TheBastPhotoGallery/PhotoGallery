package lv.photogallery.businesslogic.folder;

import java.io.File;
import java.util.Map;

public class FolderCreateRequest {
    private String folderName;
    private String file;
    private String clientEmail;

    public FolderCreateRequest(String folderName, String file, String clientEmail) {
        this.folderName = folderName;
        this.file = file;
        this.clientEmail = clientEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
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
