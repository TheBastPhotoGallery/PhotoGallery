package lv.photogallery.businesslogic;

import lv.photogallery.SpringComponentConfig;
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
import java.util.Collections;
import java.util.List;

import static java.lang.Math.toIntExact;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SpringComponentConfig.class)
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
        folder.setEmail(user);
        folder.setFolderPicture("");
        user.setFolderList(Collections.singletonList(folder));
        folder.setPicturesPathList(Collections.singletonList(picture));
        picture.setFolder(folder);
        picture.setPicturePath("testPicturePath");

        userRepository.save(user);
        folderRepository.save(folder);
        pictureRepository.save(picture);
    }

    @Test
    public void testingOfPictureRepositoryTest() {
        Collection<Folder> folders = userRepository.findByEmail("testEmail").get().getFolderList();
        assertEquals("testFolderName", folders.stream().findFirst().get().getFolderName());
        Collection<Picture> pictures = pictureRepository.findByFolderId(folders.stream().findFirst().get().getId());
        assertEquals("testPicturePath", pictures.stream().findFirst().get().getPicturePath());
    }

    @Test
    public void shouldReturnErrorWhenPictureIsNullTest() {
        PictureRefCreateRequest request = new PictureRefCreateRequest("testFolderName", null, user);
        PictureRefCreateResponse response = pictureRefCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "picture");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenPictureNameDuplicatedTest() {
        PictureRefCreateRequest request = new PictureRefCreateRequest("testFolderName", "testPicturePath", user);
        PictureRefCreateResponse response = pictureRefCreateService.create(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "picture");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }
}
