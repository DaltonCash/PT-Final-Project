package com.promineotech.art.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.entity.User;
import com.promineotech.art.service.user.UserAddUserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultUserAddUserController implements UserAddUserController {

  @Autowired
  private UserAddUserService userAddUserService;

  @Override
  public User addUser(String user_name, String password, String first_name, String last_name,
      String email) {

    log.info(
        "A request has been made to add user. Parameters: user_name = {}, password = {}, first_name = {}, last_name = {}, email = {}",
        user_name, password, first_name, last_name, email);
    log.info("Checking whether user_name or email has been used prior. user_name = {}, email = {}");



    if (userAddUserService.checkUserName(user_name)) {
      log.info("user_name: {} , is not currently in use.", user_name);
      
      if (userAddUserService.checkEmail(email)) {
        log.info("email: {} , is not currently in use. Creating user profile...", email);
        return userAddUserService.addUser(user_name, password, first_name, last_name, email);
        
      } else {
        log.info("email: {} is currently in use.", email);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The email entered is currently in use.");
        
      }
      
    } else{
      log.info("The user_name({}) entered is currently in use.", user_name);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "The user name entered is currently in use.");
    }
  }
}
