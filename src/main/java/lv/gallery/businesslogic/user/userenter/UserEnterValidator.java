package lv.gallery.businesslogic.user.userenter;
import lv.gallery.businesslogic.ValidationError;

import java.util.List;

public interface UserEnterValidator {
    List<ValidationError> validate(UserEnterRequest request);
}
