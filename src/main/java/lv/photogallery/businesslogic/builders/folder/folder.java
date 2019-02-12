package lv.photogallery.businesslogic.builders.folder;

import javax.persistence.*;

@Entity
@Table(name = "folder")
public class folder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
