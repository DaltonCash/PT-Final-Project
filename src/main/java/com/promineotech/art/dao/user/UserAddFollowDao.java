package com.promineotech.art.dao.user;

import com.promineotech.art.entity.Follow;

public interface UserAddFollowDao {

  Follow createFollow(int user_id, int seller_id);

}
