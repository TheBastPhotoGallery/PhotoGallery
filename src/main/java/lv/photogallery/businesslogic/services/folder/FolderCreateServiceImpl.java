package lv.photogallery.businesslogic.services.folder;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.controller.RestControllerExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.photogallery.businesslogic.builders.folder.FolderBuilder.createFolder;


@Component
public class FolderCreateServiceImpl implements FolderCreateService {
    @Autowired
    private FolderCreateValidator validator;
    @Autowired
    private FolderRepository folderRepository;
    private Logger logger = LoggerFactory.getLogger(FolderCreateServiceImpl.class);

    @Override
    public FolderCreateResponse create(FolderCreateRequest request) {

        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new FolderCreateResponse(validationErrors);
        }

        Folder folder = createFolder()
                .withFolderName(request.getFolderName())
                .withFiles(request.getFile())
                .withUsrId(Integer.valueOf(request.getUser().getId().intValue()))
                .build();
        folderRepository.save(folder);
        logger.info("Creation of new folder with name " + folder.getFolderName() + " in user: " + request.getUser().getEmail());

        return new FolderCreateResponse(folder.getId());
    }
}
