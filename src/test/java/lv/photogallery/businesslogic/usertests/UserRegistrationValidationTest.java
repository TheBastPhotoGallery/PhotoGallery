package lv.photogallery.businesslogic.usertests;

import lv.photogallery.SpringComponentConfig;
import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
import lv.photogallery.businesslogic.services.user.userregistration.*;
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
public class UserRegistrationValidationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRegistrationService service;
    private User user = new User();

    @Before
    public void setUp() {

        user.setEmail("test@Email");
        user.setPassword("testPassword");
        userRepository.save(user);
    }

    @Test
    public void shouldReturnErrorWhenEmailIsNullTest() {
        UserRegistrationRequest request = new UserRegistrationRequest(null, "password");
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }
    @Test

    public void shouldReturnErrorWhenPasswordIsNullTest() {
        UserRegistrationRequest request = new UserRegistrationRequest("test@Email2", null);
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "password");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }

    @Test
    public void shouldReturnErrorWhenEmailDuplicatedTest(){
        UserRegistrationRequest request = new UserRegistrationRequest("test@Email", "testPassword");
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be repeated");
    }

    @Test
    public void shouldReturnErrorWhenEmailNotContainAtTest(){
        UserRegistrationRequest request = new UserRegistrationRequest("testEmailNotValid", "testPassword");
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "email");
        assertEquals(errors.get(0).getErrorMessage(), "Must be valid");
    }

    @Test
    public void shouldReturnRegistrationTrueTest(){
        UserRegistrationRequest request = new UserRegistrationRequest("testEmail@Valid", "testPassword");
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(response.isSuccess(), true);
        assertEquals(response.getUserId().intValue(), user.getId().intValue() + 1);

    }
}
