package lv.photogallery.businesslogic.services.folder;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class FolderCreateValidatorImpl implements FolderCreateValidator {
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ValidationError> validate(FolderCreateRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validateFolderName(request.getFolderName()).ifPresent(errors::add);
        validateDuplicateFolderName(request.getFolderName(), request.getUser().getEmail()).ifPresent(errors::add);
        validateEmailNotNull(request.getUser().getEmail()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateFolderName(String name) {
        if (name == null || name.isEmpty()) {
            return Optional.of(new ValidationError("folderName", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicateFolderName(String name, String email) {
        if (name != null && !name.isEmpty()) {
            Collection<Folder> folders = folderRepository.findByUsrId(userRepository.findByEmail(email).get().getId().intValue());
            for (Folder folder : folders) {
                if (folder.getFolderName().equals(name)) {
                    return Optional.of(new ValidationError("folderName", "Must not be repeated"));
                }
            }
        }
        return Optional.empty();
    }

    private Optional<ValidationError> validateEmailNotNull(String email) {
        if (email == null || email.isEmpty()) {
            return Optional.of(new ValidationError("email", "Must not be empty"));
        }
        return Optional.empty();
    }

}
