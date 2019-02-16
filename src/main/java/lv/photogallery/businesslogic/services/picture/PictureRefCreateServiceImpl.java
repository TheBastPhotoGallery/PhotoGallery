package lv.photogallery.businesslogic.services.picture;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.database.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.photogallery.businesslogic.builders.picture.PictureBuilder.createPicture;

@Component
public class PictureRefCreateServiceImpl implements PictureRefCreateService {
    @Autowired
    private PictureRefCreateValidator validator;
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public PictureRefCreateResponse create(PictureRefCreateRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new PictureRefCreateResponse(validationErrors);
        }
        Picture picture = createPicture()
                .withPath(request.getPicturePath())
                .withFolderId(request.getFolder().getId())
                .build();

        pictureRepository.save(picture);

        return new PictureRefCreateResponse(picture.getId());
    }
}
