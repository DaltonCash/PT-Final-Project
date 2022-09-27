package com.promineotech.art.service.user;

import com.promineotech.art.entity.User;

public interface UserAddUserService {

  User addUser(String user_name, String password, String first_name, String last_name, String email);

  boolean checkEmailAndUsername(String email, String user_name);

}
