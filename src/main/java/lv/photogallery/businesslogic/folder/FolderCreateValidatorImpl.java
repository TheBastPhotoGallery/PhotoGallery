package lv.photogallery.businesslogic.folder;

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
        System.out.println(request.getClientEmail());
        List<ValidationError> errors = new ArrayList<>();
        validateFolderName(request.getFolderName()).ifPresent(errors::add);
        validateDuplicateFolderName(request.getFolderName(), request.getClientEmail()).ifPresent(errors::add);
        validateEmail(request.getClientEmail()).ifPresent(errors::add);
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
            //Collection<Folder> collection = userRepository.findByEmail(email).get().getFolderList();
            Optional<Folder> folderOpt = findFolderByName(userRepository.findByEmail(email).get().getFolderList(), name);
            if (folderOpt.isPresent()) {
                return Optional.of(new ValidationError("folderName", "Must not be repeated"));
            }
        }
        return Optional.empty();
    }
    public Optional<Folder> findFolderByName(final Collection<Folder> collection, final String name) {
        return collection.stream().filter(p -> p.getFolderName().equals(name)).findAny();
    }

    private Optional<ValidationError> validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            Optional<Folder> userOpt = folderRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                return Optional.of(new ValidationError("email", "Must not be repeated"));
            }
        } else if (email == null || email.isEmpty()) {
            return Optional.of(new ValidationError("email", "Must not be empty"));
        }
        return Optional.empty();
    }

}
