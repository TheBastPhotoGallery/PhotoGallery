package lv.photogallery.businesslogic.folder;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FolderCreateValidatorImpl implements FolderCreateValidator {
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ValidationError> validate(FolderCreateRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validateFolderName(request.getFolderName()).ifPresent(errors::add);
        validateDuplicateFolderName(request.getFolderName()).ifPresent(errors::add);
        validateEmail(request.getClientEmail()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateFolderName(String name) {
        if (name == null || name.isEmpty()) {
            return Optional.of(new ValidationError("name", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicateFolderName(String name) {
        if (name != null && !name.isEmpty()) {
            Optional<Folder> userOpt = folderRepository.findByName(name);
            if (userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }
    private Optional<ValidationError> validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Must not be repeated"));
            }
        } else if (email == null || email.isEmpty()) {
            return Optional.of(new ValidationError("email", "Must not be empty"));
        }
        return Optional.empty();
    }

}
