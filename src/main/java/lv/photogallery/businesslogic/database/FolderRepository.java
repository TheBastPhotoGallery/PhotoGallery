package lv.photogallery.businesslogic.database;

import lv.photogallery.businesslogic.builders.folder.Folder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FolderRepository extends CrudRepository<Folder, Integer> {
    Optional<Folder> findByFolderName(String name);
    Iterable<Folder> findByUsrId(Integer usrId);}


