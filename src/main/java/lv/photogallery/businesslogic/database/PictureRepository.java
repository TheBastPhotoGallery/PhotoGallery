package lv.photogallery.businesslogic.database;

import lv.photogallery.businesslogic.builders.picture.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Collection<Picture> findByFolderId(Long folderId);
    Collection<Picture> findByIdIn(List<Long> id);


}
