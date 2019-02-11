package lv.photogallery.businesslogic.user.userenter;
import lv.photogallery.businesslogic.ValidationError;

import java.util.List;

public interface UserEnterValidator {
    List<ValidationError> validate(UserEnterRequest request);
}
