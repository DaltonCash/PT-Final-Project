package com.promineotech.art.dao;

import com.promineotech.art.entity.Users;

public interface ArtUserDao {

  Users fetchUserInfo(String user_name, String password);

}
