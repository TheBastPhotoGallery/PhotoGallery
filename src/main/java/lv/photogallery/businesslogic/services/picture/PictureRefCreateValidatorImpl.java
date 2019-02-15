package lv.photogallery.businesslogic.services.picture;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class PictureRefCreateValidatorImpl implements PictureRefCreateValidator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<ValidationError> validate(PictureRefCreateRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePicture(request.getFolderName()).ifPresent(errors::add);
        validateDuplicatePicture(request.getPictureURL(), request.getUser().getEmail(), request.getFolderName()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePicture(String url) {
        if (url == null || url.isEmpty()) {
            return Optional.of(new ValidationError("picture", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicatePicture(String url, String email, String folderName) {
        if (url != null && !url.isEmpty()) {
            Collection<Folder> collection = userRepository.findByEmail(email).get().getFolderList();
            for (Folder folder : collection) {
                if (folder.getFolderName().equals(folderName)) {
                    Iterable<Picture> pictures = pictureRepository.findByFolderId(folder.getId());
                    if (StreamSupport.stream(pictures.spliterator(), false).anyMatch(p -> p.equals(url)))
                        return Optional.of(new ValidationError("picture", "Must not be repeated"));
                }
            }
        }
        return Optional.empty();
    }

}
