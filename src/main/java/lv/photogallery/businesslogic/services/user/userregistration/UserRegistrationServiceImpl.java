package lv.photogallery.businesslogic.services.user.userregistration;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.services.picture.PictureRefCreateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.photogallery.businesslogic.builders.user.UserBuilder.createUser;

@Component
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private UserRegistrationValidator validator;
    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserRegistrationResponse(validationErrors);
        }


        User user = createUser()
                .withEmail(request.getEmail())
                .withPassword(request.getPassword())
                .build();


        userRepository.save(user);

        logger.info("New user with email " + user.getEmail() + " was saved in DB");

        return new UserRegistrationResponse(user.getId());
    }

}
