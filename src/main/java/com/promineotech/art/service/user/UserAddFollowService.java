package com.promineotech.art.service.user;

import com.promineotech.art.entity.Follow;

public interface UserAddFollowService {

  Follow createFollow(int user_id, int seller_id);

}
