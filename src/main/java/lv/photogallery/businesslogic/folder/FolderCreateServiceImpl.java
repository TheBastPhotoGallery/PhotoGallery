package lv.photogallery.businesslogic.folder;

import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.database.FolderRepository;
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

    @Override
    public FolderCreateResponse create(FolderCreateRequest request) {


        List<ValidationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            return new FolderCreateResponse(validationErrors);
        }

        Folder folder = createFolder()
                .withFolderName(request.getFolderName())
                .withFiles(request.getFile())
                .withEmail(request.getClientEmail())
                .build();


        folderRepository.save(folder);

        return new FolderCreateResponse(folder.getId());
    }
}
