package lv.photogallery.businesslogic.user.userregistration;

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
        validateLogin(request.getLogin()).ifPresent(errors::add);
        validateDuplicateLogin(request.getLogin()).ifPresent(errors::add);
        validatePassword(request.getPassword()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            return Optional.of(new ValidationError("login", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Optional.of(new ValidationError("password", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicateLogin(String login) {
        if (login != null && !login.isEmpty()) {
            Optional<User> userOpt = userRepository.findByLogin(login);
            if (userOpt.isPresent()) {
                return Optional.of(new ValidationError("login", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }
}
