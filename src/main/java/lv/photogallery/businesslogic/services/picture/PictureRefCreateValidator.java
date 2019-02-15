package lv.photogallery.businesslogic.services.picture;

import lv.photogallery.businesslogic.ValidationError;
import java.util.List;

public interface PictureRefCreateValidator {
    List<ValidationError> validate(PictureRefCreateRequest request);
}
