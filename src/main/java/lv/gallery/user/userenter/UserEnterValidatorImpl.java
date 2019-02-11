package lv.gallery.user.userenter;
import lv.gallery.builders.user.User;
import lv.gallery.ValidationError;
import lv.gallery.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
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
        // System.out.println(userRepository.findByLogin("slavaTest").get());
        List<ValidationError> errors = new ArrayList<>();
        validateLogin(request.getLogin()).ifPresent(errors::add);
        validatePassword(request.getPassword()).ifPresent(errors::add);
        validateLoginWithPassword(request.getLogin(), request.getPassword()).ifPresent(errors::add);

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

    private Optional<ValidationError> validateLoginWithPassword(String login, String password) {
        if (login != null && !login.isEmpty()) {
            Optional<User> userOpt = userRepository.findByLogin(login);
            if (!userOpt.isPresent()) {
                return Optional.of(new ValidationError("login", "Such login not found"));
            }
            if (!userOpt.get().getPassword().equals(password)){
                return  Optional.of(new ValidationError("password", "Incorrect password"));
            }
        }
        return Optional.empty();
    }
}
