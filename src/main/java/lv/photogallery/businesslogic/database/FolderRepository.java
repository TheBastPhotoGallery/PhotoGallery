package lv.photogallery.businesslogic.database;

import lv.photogallery.businesslogic.builders.folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
    Optional<Folder> findByFolderName(String name);

    Collection<Folder> findByUsrId(Integer usrId);
}


