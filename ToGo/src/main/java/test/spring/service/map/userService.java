package test.spring.service.map;

import test.spring.component.choi.LoginDTO;
import test.spring.component.map.userDTO;

import java.util.List;

public interface userService {
    public userDTO profile_inquiry(String id);
    public int add_user_schedule(userDTO dto);
}
