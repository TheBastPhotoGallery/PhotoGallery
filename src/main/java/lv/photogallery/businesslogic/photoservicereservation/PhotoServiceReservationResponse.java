package lv.photogallery.businesslogic.photoservicereservation;

import lv.photogallery.businesslogic.ValidationError;
import java.util.List;

public class PhotoServiceReservationResponse {
    private Long userId;

    private boolean success;

    private List<ValidationError> errors;

    public PhotoServiceReservationResponse(Long userId) {
        this.userId = userId;
        this.success = true;
        this.errors = null;
    }

    public PhotoServiceReservationResponse(List<ValidationError> errors) {
        this.userId = null;
        this.success = false;
        this.errors = errors;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
