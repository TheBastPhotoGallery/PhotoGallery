package lv.photogallery.businesslogic.database;

import lv.photogallery.businesslogic.builders.folder.Folder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FolderRepository extends CrudRepository<Folder, Integer> {
    Optional<Folder> findByName(String email);}

