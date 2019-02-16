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

@Component
public class PictureRefCreateValidatorImpl implements PictureRefCreateValidator {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<ValidationError> validate(PictureRefCreateRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePictureNotEmpty(request.getPicturePath()).ifPresent(errors::add);
        validateDuplicatePicture(request.getPicturePath(), request.getUser().getEmail(), request.getFolder().getFolderName()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePictureNotEmpty(String url) {
        if (url == null || url.isEmpty()) {
            return Optional.of(new ValidationError("picture", "Must not be empty"));
        } else {
            return Optional.empty();
        }
    }

    private Optional<ValidationError> validateDuplicatePicture(String url, String email, String folderName) {
        if (url != null && !url.isEmpty()) {
            Iterable<Folder> folders = folderRepository.findByUsrId(userRepository.findByEmail(email).get().getId().intValue());

            for (Folder folder : folders) {
                if (folder.getFolderName().equals(folderName)) {
                    Collection<Picture> pictures = pictureRepository.findByFolderId(folder.getId());
                    for (Picture p : pictures){
                        if (p.getPicturePath().equals(url))
                            return Optional.of(new ValidationError("picture", "Must not be repeated"));
                    }
                }
            }
        }
        return Optional.empty();
    }

}
