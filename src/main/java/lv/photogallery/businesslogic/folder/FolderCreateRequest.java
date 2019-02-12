package lv.photogallery.businesslogic.folder;

import java.io.File;
import java.util.Map;

public class FolderCreateRequest {
    private String folderName;
    private Map<Integer, File> file;
    private String clientEmail;

    public FolderCreateRequest(String folderNmae, Map<Integer, File> file, String clientEmail) {
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

    public Map<Integer, File> getFile() {
        return file;
    }

    public void setFile(Map<Integer, File> file) {
        this.file = file;
    }
}
