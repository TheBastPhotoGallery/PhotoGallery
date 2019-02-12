package lv.photogallery.businesslogic.foldertests;

import lv.photogallery.SpringComponentConfig;
import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.folder.FolderCreateRequest;
import lv.photogallery.businesslogic.folder.FolderCreateResponse;
import lv.photogallery.businesslogic.folder.FolderCreateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SpringComponentConfig.class)
public class FolderCreationValidationTest {
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private FolderCreateService folderCreateService;
    private Folder folder = new Folder();

    @Before
    public void setUp() {

        folder.setEmail("test@email.lv");
        folder.setFiles(null);
        folder.setFolderName("testFolderName");
        folderRepository.save(folder);
    }

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        FolderCreateRequest request = new FolderCreateRequest(null, null, "testEmail");
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "folderName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test

    public void shouldReturnErrorWhenPasswordIsNull() {
        FolderCreateRequest request = new FolderCreateRequest("folderName", null, null);
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenEmailDuplicated() {
        FolderCreateRequest request = new FolderCreateRequest("folderNameTest", null, "test@email.lv");
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void shouldReturnErrorWhenFolderNameDuplicated() {
        FolderCreateRequest request = new FolderCreateRequest("testFolderName", null, "testEmail.lv");
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "folderName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }
}
