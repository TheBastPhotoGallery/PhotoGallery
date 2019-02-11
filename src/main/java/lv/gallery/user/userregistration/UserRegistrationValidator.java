package lv.gallery.user.userregistration;

import lv.gallery.ValidationError;

import java.util.List;

public interface UserRegistrationValidator {
    List<ValidationError> validate(UserRegistrationRequest request);
}
