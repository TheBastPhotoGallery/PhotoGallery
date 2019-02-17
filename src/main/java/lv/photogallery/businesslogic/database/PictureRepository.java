package lv.photogallery.businesslogic.database;

import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Integer> {
    Collection<Picture> findByFolderId(Long folderId);


}
