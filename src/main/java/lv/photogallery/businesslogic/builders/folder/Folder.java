package lv.photogallery.businesslogic.builders.folder;

import lv.photogallery.businesslogic.builders.user.User;

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

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    //@Column(name = "email", nullable = false)
    //private String email;
    @JoinColumn(name = "EMAIL")
    private User email;

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
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

    public void setFiles(String files) {
        this.files = files;
    }


}
