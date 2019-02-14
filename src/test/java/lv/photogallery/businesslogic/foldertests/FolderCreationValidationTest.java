package lv.photogallery.businesslogic.foldertests;

import lv.photogallery.SpringComponentConfig;
import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.folder.FolderCreateRequest;
import lv.photogallery.businesslogic.folder.FolderCreateResponse;
import lv.photogallery.businesslogic.folder.FolderCreateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
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
    @Autowired
    private UserRepository userRepository;

    private Folder folder = new Folder();
    private User user = new User();

    @Before
    public void setUp() {
        user.setEmail("testEmail");
        user.setPassword("testPassword");
        folder.setFolderName("testFolderName");
        //folder.setEmail(user).;
        //folderRepository.save(folder);
        user.setFolderList(Collections.singletonList(folder));
        userRepository.save(user);
    }


   @Rollback(false)
   @Transactional
    @Test
    public void testingOfFolderRepository(){
        assertEquals("testFolderName", folderRepository.findByFolderName("testFolderName").get().getFolderName());

    }

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        FolderCreateRequest request = new FolderCreateRequest(null, null,  user);
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "folderName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test

    public void shouldReturnErrorWhenPasswordIsNull() {

        FolderCreateRequest request = new FolderCreateRequest("folderName", null, user);
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenEmailDuplicated() {
        FolderCreateRequest request = new FolderCreateRequest("folderNameTest", null,  user);
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void shouldReturnErrorWhenFolderNameDuplicated() {
        FolderCreateRequest request = new FolderCreateRequest("testFolderName", null, user);
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "folderName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void shouldReturnError() {
        FolderCreateRequest request = new FolderCreateRequest("1testFolderName", null, user);
        FolderCreateResponse response = folderCreateService.create(request);

        Collection<Folder> collection = userRepository.findByEmail(user.getEmail()).get().getFolderList();

        for (Folder folder : collection) {
            System.out.println(folder.getFolderName());
        }

    }
}
