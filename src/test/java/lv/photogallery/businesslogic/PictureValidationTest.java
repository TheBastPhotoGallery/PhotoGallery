package lv.photogallery.businesslogic;

import lv.photogallery.SpringComponentForTestConfig;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.services.picture.PictureRefCreateRequest;
import lv.photogallery.businesslogic.services.picture.PictureRefCreateResponse;
import lv.photogallery.businesslogic.services.picture.PictureRefCreateService;
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
public class PictureValidationTest {
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private PictureRefCreateService pictureRefCreateService;

    private Folder folder = new Folder();
    private User user = new User();
    private Picture picture = new Picture();


    @Before
    public void setUp() {
        user.setEmail("testEmail");
        user.setPassword("testPassword");
        folder.setFolderName("testFolderName");
        folder.setFolderPicture("");
        picture.setPicturePath("testPicturePath");

        userRepository.save(user);
        folder.setUsrId(user.getId().intValue());
        folderRepository.save(folder);
        picture.setFolderId(folder.getId());
        pictureRepository.save(picture);
    }

    @Test
    public void testingOfPictureRepositoryTest() {

        Iterable<Folder> folders = folderRepository.findByUsrId(userRepository.findByEmail("testEmail").get().getId().intValue());
        assertEquals("testFolderName", folders.iterator().next().getFolderName());
        Long folderId = folders.iterator().next().getId();
        Collection<Picture> pictures = pictureRepository.findByFolderId(folderId);
        assertEquals("testPicturePath", pictures.stream().findFirst().get().getPicturePath());
        
    }

    @Test
    public void shouldReturnErrorWhenPictureIsNullTest() {
        PictureRefCreateRequest request = new PictureRefCreateRequest(folder, null, user);
        PictureRefCreateResponse response = pictureRefCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "picture");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenPictureNameDuplicatedTest() {
        PictureRefCreateRequest request = new PictureRefCreateRequest(folder, "testPicturePath", user);
        PictureRefCreateResponse response = pictureRefCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "picture");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void shouldReturnManyPictureForFolderTest(){
        PictureRefCreateRequest request = new PictureRefCreateRequest(folder, "testPicturePath2", user);
        PictureRefCreateResponse response = pictureRefCreateService.create(request);
        assertEquals(response.isSuccess(), true);
        Collection<Picture> pictures = pictureRepository.findByFolderId(folder.getId());
        assertEquals(pictures.iterator().next().getPicturePath(),"testPicturePath");
        assertEquals(pictures.stream().skip(1).findFirst().orElse(null).getPicturePath(),"testPicturePath2");
    }
}
