package lv.gallery.user.userenter;
import lv.gallery.ValidationError;
import org.springframework.validation.Errors;

import java.util.List;

public interface UserEnterValidator {
    List<ValidationError> validate(UserEnterRequest request);
}
