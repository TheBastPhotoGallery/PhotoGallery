package lv.photogallery.businesslogic.folder;

import lv.photogallery.businesslogic.ValidationError;
import java.util.List;

public interface FolderCreateValidator {
    List<ValidationError> validate(FolderCreateRequest request);
}
