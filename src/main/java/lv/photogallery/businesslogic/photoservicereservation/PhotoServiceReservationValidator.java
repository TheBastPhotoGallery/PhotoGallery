package lv.photogallery.businesslogic.photoservicereservation;

import lv.photogallery.businesslogic.ValidationError;

import java.util.List;

public interface PhotoServiceReservationValidator {
    List<ValidationError> validate(PhotoServiceReservationRequest request);
}
