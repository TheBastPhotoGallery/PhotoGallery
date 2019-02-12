package lv.photogallery.realdatabasetest;

import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRealRepositoryInputValidateTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void inputToRepoTest(){

        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPass");

        userRepository.save(user);

        assertThat(userRepository.findByLogin(user.getLogin())).get().isEqualTo(user);

    }

}
