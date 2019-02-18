package lv.photogallery.businesslogic.services.picture;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.services.folder.FolderCreateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(PictureRefCreateServiceImpl.class);

    @Override
    public PictureRefCreateResponse create(PictureRefCreateRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new PictureRefCreateResponse(validationErrors);
        }
        Picture picture = createPicture()
                .withPath(request.getPicturePath())
                .withFolderId(request.getFolder().getId())
                .withCheckBox(0)
                .build();

        pictureRepository.save(picture);
        logger.info("New picture was saved in DB with name " + picture.getPicturePath() + " in folder id: " + picture.getFolderId());

        return new PictureRefCreateResponse(picture.getId());
    }
}
