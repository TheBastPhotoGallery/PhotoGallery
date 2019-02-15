package lv.photogallery.businesslogic.services.user.userregistration;

import lv.photogallery.businesslogic.ValidationError;

import java.util.List;

public interface UserRegistrationValidator {
    List<ValidationError> validate(UserRegistrationRequest request);
}
