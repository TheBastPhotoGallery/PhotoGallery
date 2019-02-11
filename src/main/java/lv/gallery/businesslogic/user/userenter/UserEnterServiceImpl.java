package lv.gallery.businesslogic.user.userenter;

import lv.gallery.businesslogic.ValidationError;
import lv.gallery.businesslogic.builders.user.User;
import lv.gallery.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEnterServiceImpl implements UserEnterService {
    @Autowired
    private UserEnterValidator validator;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEnterResponse enter(UserEnterRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new UserEnterResponse(validationErrors);
        }

        User user =  userRepository.findByLogin(request.getLogin()).get();

        return new UserEnterResponse(user.getId());
    }

}
