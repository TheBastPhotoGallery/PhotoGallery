package lv.photogallery.businesslogic.services.user.userregistration;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRegistrationValidationImpl implements UserRegistrationValidator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(UserRegistrationRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validateDuplicateEmail(request.getEmail()).ifPresent(errors::add);
        validatePassword(request.getPassword()).ifPresent(errors::add);
        validateEmail(request.getEmail()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Optional.of(new ValidationError("password", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return Optional.of(new ValidationError("email", "Must not be empty"));
        }else {
            return Optional.empty();
        }
    }
}
