package lv.photogallery.businesslogic.services.user.userenter;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.services.user.userregistration.UserRegistrationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEnterServiceImpl implements UserEnterService {
    @Autowired
    private UserEnterValidator validator;
    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserEnterServiceImpl.class);

    @Override
    public UserEnterResponse enter(UserEnterRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserEnterResponse(validationErrors);
        }

        User user = userRepository.findByEmail(request.getEmail()).get();
        logger.info("User " + user.getEmail() + " logged in");


        return new UserEnterResponse(user.getId());
    }

}
