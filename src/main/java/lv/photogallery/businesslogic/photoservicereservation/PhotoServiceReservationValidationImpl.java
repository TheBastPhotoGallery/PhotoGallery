package lv.photogallery.businesslogic.photoservicereservation;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class PhotoServiceReservationValidationImpl implements PhotoServiceReservationValidator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ValidationError> validate(PhotoServiceReservationRequest request) {
        List<ValidationError> errors = new ArrayList<>();

        validateService(request.getService()).ifPresent(errors::add);
        validateDateTime(request.getDateTime()).ifPresent(errors::add);
        validateEmail(request.getEmail()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateService(String service) {
        if (service == null || service.isEmpty()) {
            return Optional.of(new ValidationError("service", "This field must be completed!"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDateTime(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            return Optional.of(new ValidationError("dateTime", "This field must be completed!"));
        } else {
            if (dateTime.length() > 16) {
                return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
            }
            if (!Pattern.matches("^[2][0][1-9][0-9][-][0-1][0-9][-][0-3][0-9][ ][1][0-7][:][0][0]", dateTime)) {
                return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
            }
            else {
                char[] dateTimeArray= dateTime.toCharArray();
                if((dateTimeArray[5]== '0')||((dateTimeArray[5]== '1') && ((dateTimeArray[6]== '0')||(dateTimeArray[6]== '1')||(dateTimeArray[6]== '2')))) {
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '1')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '3')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '4')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '5')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '6')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '7')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '8')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '9')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '1')&&(dateTimeArray[6]== '0')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '1')&&(dateTimeArray[6]== '1')&&(dateTimeArray[8]== '3')&&(dateTimeArray[9]!= '0')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '1')&&(dateTimeArray[6]== '2')&&(dateTimeArray[8]== '3')&&((dateTimeArray[9]!= '0')||(dateTimeArray[9]!= '1'))) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '2')&&(dateTimeArray[8]== '3')) {
                        return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                    }
                    if ((dateTimeArray[5]== '0')&&(dateTimeArray[6]== '2')&&(dateTimeArray[8]== '2')&&(dateTimeArray[9]== '9')) {
                        int userNumber= Integer.parseInt(dateTime.substring(2,4));
                        if (userNumber%4!= 0) {
                            return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                        }
                    }
                    return Optional.empty();
                }
                else {
                    return Optional.of(new ValidationError("dateTime", "Date/Time format error!"));
                }
            }
        }
    }

    private Optional<ValidationError> validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (!userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "You are not registered!"));
            }
        }

        return Optional.empty();
    }
}

