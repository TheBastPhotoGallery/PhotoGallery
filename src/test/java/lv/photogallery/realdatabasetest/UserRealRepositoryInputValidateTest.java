package lv.photogallery.realdatabasetest;

import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.picture.Picture;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.FolderRepository;
import lv.photogallery.businesslogic.database.PictureRepository;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.services.folder.FolderCreateRequest;
import lv.photogallery.businesslogic.services.folder.FolderCreateResponse;
import lv.photogallery.businesslogic.services.folder.FolderCreateService;
import lv.photogallery.businesslogic.services.picture.PictureRefCreateRequest;
import lv.photogallery.businesslogic.services.picture.PictureRefCreateResponse;
import lv.photogallery.businesslogic.services.picture.PictureRefCreateService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRealRepositoryInputValidateTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private FolderCreateService folderCreateService;
    @Autowired
    private PictureRefCreateService pictureRefCreateService;

    public String email1 = "test1@Email";
    public String email2 = "test2@Email";


    @Test
    @Rollback(false)
    public void saveUsers(){
        User user = new User();
        user.setEmail(email1);
        user.setPassword("testPass");
        userRepository.save(user);
        //assertThat(userRepository.findByEmail(user.getEmail())).get().isEqualTo(user);
        User user2 = new User();
        user2.setEmail(email2);
        user2.setPassword("testPass");
        userRepository.save(user2);
        //assertThat(userRepository.findByEmail(user2.getEmail())).get().isEqualTo(user2);
        //assertEquals(userRepository.findByEmail(email1).get().getPassword(), "testPass");
        //assertEquals(userRepository.findByEmail(email2).get().getPassword(), "testPass");
        //String name = userRepository.findByEmail(email1).get().getEmail();
    }



    @Test
    @Rollback(false)
    public void checkUsersPasswordTest(){
        System.out.println(userRepository.findByEmail(email1).get().getPassword());
       // assertEquals(userRepository.findByEmail(email1).get().getPassword(), "testPass");
        //assertEquals(userRepository.findByEmail(email2).get().getPassword(), "testPass");
    }


    @Test
    @Rollback(false)
    public void createUsersFoldersInRepositoryTest(){
        System.out.println(userRepository.findByEmail(email1).get().getPassword());
        User userss = userRepository.findByEmail(email1).get();
        FolderCreateRequest folderCreateRequest = new FolderCreateRequest("user1FolderName", "", userss);

        FolderCreateResponse folderCreateResponse = folderCreateService.create(folderCreateRequest);
        assertTrue(folderCreateResponse.isSuccess());
        FolderCreateRequest folderCreateRequest1 = new FolderCreateRequest("user1FolderName2", "", userRepository.findByEmail(email2).get());
        FolderCreateResponse folderCreateResponse1 = folderCreateService.create(folderCreateRequest1);
        assertTrue(folderCreateResponse1.isSuccess());

    }

    @Test
    @Rollback(false)
    public void createUserFoldersPicturesTest(){
        PictureRefCreateRequest request = new PictureRefCreateRequest(folderRepository.findByFolderName("user1FolderName2").get(), "picturePath",
                userRepository.findByEmail(email1).get());
        PictureRefCreateResponse response = pictureRefCreateService.create(request);
        assertTrue(response.isSuccess());
        PictureRefCreateRequest request2 = new PictureRefCreateRequest(folderRepository.findByFolderName("user1FolderName2").get(), "picturePath2",
                userRepository.findByEmail(email1).get());
        PictureRefCreateResponse response2 = pictureRefCreateService.create(request2);
        assertTrue(response2.isSuccess());

        Collection<Picture> pictures = pictureRepository.findByFolderId(folderRepository.findByFolderName("user1FolderName2").get().getId());
        assertEquals(pictures.iterator().next().getPicturePath(),"picturePath");
        assertEquals(pictures.stream().skip(1).findFirst().orElse(null).getPicturePath(),"picturePath2");

    }

}
