package lv.photogallery.businesslogic.foldertests;

import lv.photogallery.SpringComponentForTestConfig;
import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.services.folder.FolderCreateRequest;
import lv.photogallery.businesslogic.services.folder.FolderCreateResponse;
import lv.photogallery.businesslogic.services.folder.FolderCreateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SpringComponentForTestConfig.class)
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
        userRepository.save(user);
        folder.setUsrId(user.getId().intValue());
        folder.setFolderName("testFolderName");
        folder.setFolderPicture("");
        folderRepository.save(folder);
    }

    @Test
    public void testingOfFolderRepositoryTest(){
        assertEquals("testFolderName", folderRepository.findByFolderName("testFolderName").get().getFolderName());
        userRepository.findByEmail("testEmail").get().getId();
        Collection<Folder> folders = folderRepository.findByUsrId(userRepository.findByEmail("testEmail").get().getId().intValue());
        assertEquals(folders.iterator().next().getFolderName(), "testFolderName");
    }

    @Test
    public void shouldReturnErrorWhenNameIsNullTest() {
        FolderCreateRequest request = new FolderCreateRequest(null, null,  user);
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "folderName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenFolderNameDuplicatedTest() {
        FolderCreateRequest request = new FolderCreateRequest("testFolderName", null, user);
        FolderCreateResponse response = folderCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "folderName");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void shouldReturnManyFoldersForOneUserTest(){
        FolderCreateRequest request = new FolderCreateRequest("testFolderName2", null, user);
        FolderCreateResponse response = folderCreateService.create(request);
        assertEquals(response.isSuccess(), true);
        Collection<Folder> folders = folderRepository.findByUsrId(user.getId().intValue());
        //folders.forEach (e -> System.out.println(e.getFolderName()) );
        assertEquals(folders.iterator().next().getFolderName(),"testFolderName");
        assertEquals(folders.stream().skip(1).findFirst().orElse(null).getFolderName(),"testFolderName2");
    }

}
