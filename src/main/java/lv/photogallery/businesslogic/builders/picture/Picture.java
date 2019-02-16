package lv.photogallery.businesslogic.builders.picture;

import lv.photogallery.businesslogic.builders.folder.Folder;

import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "picturePath", nullable = false)
    private String picturePath;

    @Column(name = "folderId")
    private Long folderId;

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }
//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "folder_id")
//    private Folder folder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


//    public Folder getFolder() {
//        return folder;
//    }
//
//    public void setFolder(Folder folder) {
//        this.folder = folder;
//    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
