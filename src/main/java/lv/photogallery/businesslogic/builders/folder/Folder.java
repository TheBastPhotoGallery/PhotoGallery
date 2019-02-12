package lv.photogallery.businesslogic.builders.folder;

import javax.persistence.*;
import java.io.File;
import java.util.Map;

@Entity
@Table(name = "Folder")
public class Folder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "folderName", nullable = false)
    private String foldername;

    @Column(name = "files")
    private Map<Integer, File> files;

    @Column(name = "email", nullable = false)
    private String clientEmail;

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Integer, File> getFiles() {
        return files;
    }

    public void setFiles(Map<Integer, File> files) {
        this.files = files;
    }


}
