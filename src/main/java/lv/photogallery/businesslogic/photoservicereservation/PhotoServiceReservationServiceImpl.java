package lv.photogallery.businesslogic.photoservicereservation;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.calendar.CalendarEditor;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.email.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhotoServiceReservationServiceImpl implements PhotoServiceReservationService {
    @Autowired
    private PhotoServiceReservationValidator validator;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PhotoServiceReservationResponse reserve(PhotoServiceReservationRequest request) {

        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new PhotoServiceReservationResponse(validationErrors);
        }

        try {
            if (!CalendarEditor.isPhotoServiceReserved(request.getDateTime())) {
                validationErrors = new ArrayList<>();
                validationErrors.add(new ValidationError("dateTime", "Sorry, your desired time is booked!"));
                return new PhotoServiceReservationResponse(validationErrors);
            }
        } catch (Exception e) {
            validationErrors = new ArrayList<>();
            validationErrors.add(new ValidationError("dateTime", "Input error!"));
            return new PhotoServiceReservationResponse(validationErrors);
        }

        String dateTimeFormat= request.getDateTime().substring(0,10) + " at" + request.getDateTime().substring(10,16);
        StringBuilder sb = new StringBuilder();
        sb.append("Dear Customer!<br/> \n");
        sb.append("<br/>\n");
        sb.append("Thank You for Your Order!<br/>\n");
        sb.append("We are waiting for Your " + request.getService() + " photo session on " + dateTimeFormat + "!<br/>\n");
        sb.append("<br/>\n");
        sb.append("Best regards,<br/>\n");
        sb.append("Your PhotoGallery Team<br/>\n");
        if (!SendEmail.SendMailMessage(sb, request.getEmail())) {
            validationErrors = new ArrayList<>();
            validationErrors.add(new ValidationError("email", "Email error!"));
            return new PhotoServiceReservationResponse(validationErrors);
        }

        User user =  userRepository.findByEmail(request.getEmail()).get();

        return new PhotoServiceReservationResponse(user.getId());
    }
}


