package lv.photogallery.businesslogic;

import lv.photogallery.SpringComponentConfig;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.user.userenter.UserEnterRequest;
import lv.photogallery.businesslogic.user.userenter.UserEnterResponse;
import lv.photogallery.businesslogic.user.userenter.UserEnterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SpringComponentConfig.class)
public class UserEnterValidationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEnterService userEnterService;
    private User user = new User();

    @Before
    public void setUp() {

        user.setLogin("testLogin");
        user.setPassword("testPassword");
        userRepository.save(user);
    }

    @Test
    public void finedByLoginInRepositoryTest() {
        User lookingUser = userRepository.findByLogin("testLogin").get();
        assertThat(lookingUser.getLogin()).isEqualTo("testLogin");
    }

    @Test
    public void UserPasswordValidationTest() {
        UserEnterRequest request = new UserEnterRequest("testLogin", "wrongpassword");
        UserEnterResponse response = userEnterService.enter(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getErrorMessage(), "Incorrect password");
    }
}