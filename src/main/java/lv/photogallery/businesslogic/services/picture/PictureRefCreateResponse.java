package lv.photogallery.businesslogic.services.picture;

import lv.photogallery.businesslogic.ValidationError;

import java.util.List;

public class PictureRefCreateResponse {
    private Long pictureId;

    private boolean success;

    private List<ValidationError> errors;

    public PictureRefCreateResponse(Long folderId) {
        this.pictureId = folderId;
        this.success = true;
        this.errors = null;
    }

    public PictureRefCreateResponse(List<ValidationError> errors) {
        this.pictureId = null;
        this.success = false;
        this.errors = errors;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
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
