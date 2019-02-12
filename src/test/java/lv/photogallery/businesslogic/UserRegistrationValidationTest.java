package lv.photogallery.businesslogic;

import lv.photogallery.SpringComponentConfig;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.user.userregistration.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SpringComponentConfig.class)
public class UserRegistrationValidationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRegistrationService service;
    private User user = new User();

    @Before
    public void setUp() {

        user.setEmail("testEmail");
        user.setPassword("testPassword");
        userRepository.save(user);
    }

    @Test
    public void shouldReturnErrorWhenEmailIsNull() {
        UserRegistrationRequest request = new UserRegistrationRequest(null, "password");
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }
    @Test

    public void shouldReturnErrorWhenPasswordIsNull() {
        UserRegistrationRequest request = new UserRegistrationRequest("testEmail2", null);
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenEmailDuplicated(){
        UserRegistrationRequest request = new UserRegistrationRequest("testEmail", "testPassword");
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }
}
