package lv.photogallery.controller;

import lv.photogallery.businesslogic.builders.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerExample {
    private Logger logger = LoggerFactory.getLogger(RestControllerExample.class);
    @PostMapping("/")
    public void postLogin(@RequestBody User user) {

        logger.info(user.getEmail());
    }
}
