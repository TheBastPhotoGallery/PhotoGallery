package lv.photogallery.businesslogic.foldertests;

import lv.photogallery.SpringComponentConfig;
import lv.photogallery.businesslogic.builders.folder.Folder;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.repository.FieldRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SpringComponentConfig.class)
public class FinedUsersFolderTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FieldRepository fieldRepository;

    User user = new User();
    Folder folder = new Folder();

    @Before

    public void setUp() {
        user.setEmail("test@email.lv");
        user.setPassword("testPassword");
        userRepository.save(user);

    }
}
