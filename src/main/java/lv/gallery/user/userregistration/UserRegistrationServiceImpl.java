package lv.gallery.user.userregistration;

import lv.gallery.ValidationError;
import lv.gallery.builders.user.User;
import lv.gallery.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.gallery.builders.user.UserBuilder.createUser;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private UserRegistrationValidator validator;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserRegistrationResponse(validationErrors);
        }


        User user = createUser()
                .withLogin(request.getLogin())
                .withPassword(request.getPassword())
                .build();


        userRepository.save(user);

        return new UserRegistrationResponse(user.getId());
    }

}
