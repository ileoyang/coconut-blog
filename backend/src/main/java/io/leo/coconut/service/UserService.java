package io.leo.coconut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.leo.coconut.model.dto.LoginDto;
import io.leo.coconut.model.dto.RegisterDto;
import io.leo.coconut.model.entity.User;

/**
 * @author Leo
 */
public interface UserService extends IService<User> {

    /**
     * User register.
     * @param registerDto
     * @return new user object if success else null
     */
    User register(RegisterDto registerDto);

    /**
     * User login.
     * @param loginDto
     * @return jwt if success else null
     */
    String login(LoginDto loginDto);

}
