package lv.photogallery.businesslogic.photoservicereservation;

import lv.photogallery.SpringComponentForTestConfig;
import lv.photogallery.businesslogic.ValidationError;
import lv.photogallery.businesslogic.builders.user.User;
import lv.photogallery.businesslogic.database.UserRepository;
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
    @Import(SpringComponentForTestConfig.class)
    public class PhotoServiceValidationTests {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private PhotoServiceReservationService service;
        private User user = new User();

        @Before
        public void setUp() {
            user.setEmail("ajup@inbox.lv");
            user.setPassword("testPassword");
            userRepository.save(user);
        }

        @Test
        public void shouldReturnErrorWhenDateError(){
            PhotoServiceReservationRequest request = new PhotoServiceReservationRequest("Kids", "  2019-02-28 10:00  ", "ajup@inbox.lv");
            PhotoServiceReservationResponse response = service.reserve(request);
            List<ValidationError> errors = response.getErrors();
 //           assertEquals(errors.size(), 1);
             assertEquals(errors.get(0).getField(), "dateTime");
            assertEquals(errors.get(0).getErrorMessage(), "Date/Time format error!");
        }
    }

