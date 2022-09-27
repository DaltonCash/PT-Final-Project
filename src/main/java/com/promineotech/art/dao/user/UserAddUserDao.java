package com.promineotech.art.dao.user;

import java.util.List;
import com.promineotech.art.entity.User;

public interface UserAddUserDao {

  List<User> fetchUsers();

  User addUser(String user_name, String password, String first_name, String last_name,
      String email);

}
