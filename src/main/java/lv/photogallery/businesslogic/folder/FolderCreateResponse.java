package lv.photogallery.businesslogic.folder;

import lv.photogallery.businesslogic.ValidationError;

import java.util.List;

public class FolderCreateResponse {
    private Long folderId;

    private boolean success;

    private List<ValidationError> errors;

    public FolderCreateResponse(Long folderId) {
        this.folderId = folderId;
        this.success = true;
        this.errors = null;
    }

    public FolderCreateResponse(List<ValidationError> errors) {
        this.folderId = null;
        this.success = false;
        this.errors = errors;
    }

}




