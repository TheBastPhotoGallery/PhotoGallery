package lv.photogallery.businesslogic.builders.folder;

import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.builders.user.User;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "folderName", nullable = false)
    private String folderName;

    @Column(name = "files")
    private String files;

    @Column(name = "usrId")
    private Integer usrId;

    @OneToMany(mappedBy = "pictureURL", fetch = FetchType.EAGER)
    private Collection<Picture> picturesURLList;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMAIL")
    private User email;

    public Collection<Picture> getPicturesURLList() {
        return picturesURLList;
    }

    public void setPicturesURLList(Collection<Picture> picturesURLList) {
        this.picturesURLList = picturesURLList;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

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
