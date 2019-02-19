package lv.photogallery.businesslogic.services.user.userenter;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserEnterValidatorImpl implements UserEnterValidator {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ValidationError> validate(UserEnterRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validateEmail((request.getEmail())).ifPresent(errors::add);
        validatePassword(request.getPassword()).ifPresent(errors::add);
        validateEmailWithPassword(request.getEmail(), request.getPassword()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Optional.of(new ValidationError("password", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateEmailWithPassword(String email, String password) {
        if (email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (!userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Such email not found"));
            }
            if (!userOpt.get().getPassword().equals(password)){
                return  Optional.of(new ValidationError("password", "Incorrect password"));
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
