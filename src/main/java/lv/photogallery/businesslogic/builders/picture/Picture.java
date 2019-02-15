package lv.photogallery.businesslogic.builders.picture;

import lv.photogallery.businesslogic.builders.folder.Folder;
import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pictureurl")
    private String pictureURL;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
