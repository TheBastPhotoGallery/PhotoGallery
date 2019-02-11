package lv;

import lv.gallery.PhotoGalleryApplication;
import lv.gallery.ValidationError;
import lv.gallery.user.userregistration.UserRegistrationRequest;
import lv.gallery.user.userregistration.UserRegistrationResponse;
import lv.gallery.user.userregistration.UserRegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={PhotoGalleryApplication.class})
public class UserRegistrationValidationTest {

    @Autowired
    private UserRegistrationService service;

    @Test
    public void shouldReturnErrorWhenLoginIsNull() {
        UserRegistrationRequest request = new UserRegistrationRequest(null, "password");
        UserRegistrationResponse response = service.register(request);
        List<ValidationError> errors = response.getErrors();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "login");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty");
    }
}
