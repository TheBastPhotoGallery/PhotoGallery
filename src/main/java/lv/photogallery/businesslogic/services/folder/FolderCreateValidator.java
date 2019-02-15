package lv.photogallery.businesslogic.services.folder;

import lv.photogallery.businesslogic.ValidationError;
import java.util.List;

public interface FolderCreateValidator {
    List<ValidationError> validate(FolderCreateRequest request);
}
