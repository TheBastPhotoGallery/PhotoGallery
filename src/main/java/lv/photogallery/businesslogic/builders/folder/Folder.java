package lv.photogallery.businesslogic.builders.folder;


import javax.persistence.*;

@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "foldername", nullable = false)
    private String folderName;

    @Column(name = "files")
    private String files;

    @Column(name = "usrid")
    private Integer usrId;

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
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

    public void setFolderPicture(String files) {
        this.files = files;
    }
}
