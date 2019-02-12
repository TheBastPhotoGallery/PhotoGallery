package lv.photogallery.businesslogic.builders.folder;

import javax.persistence.*;

@Entity
@Table(name = "Folder")
public class Folder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "folderName", nullable = false)
    private String folderName;

    @Column(name = "files")
    private String files;

    @Column(name = "email", nullable = false)
    private String email;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }


}
