package lv.photogallery.businesslogic.database;

import lv.photogallery.businesslogic.upload.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}