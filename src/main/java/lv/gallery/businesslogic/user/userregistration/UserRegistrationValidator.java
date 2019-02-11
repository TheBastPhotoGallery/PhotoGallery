package lv.gallery.businesslogic.user.userregistration;

import lv.gallery.businesslogic.ValidationError;

import java.util.List;

public interface UserRegistrationValidator {
    List<ValidationError> validate(UserRegistrationRequest request);
}
