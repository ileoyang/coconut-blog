package io.leo.coconut.controller;

import io.leo.coconut.common.Result;
import io.leo.coconut.model.dto.LoginDto;
import io.leo.coconut.model.dto.RegisterDto;
import io.leo.coconut.model.entity.User;
import io.leo.coconut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Leo
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public Result register(@Valid @RequestBody RegisterDto registerDto) {
        User user = userService.register(registerDto);
        if (user == null) {
            return Result.fail("failed to register");
        }
        return Result.success(user);
    }

    @PostMapping("login")
    public Result login(@Valid @RequestBody LoginDto loginDto) {
        String jwt = userService.login(loginDto);
        if (jwt == null) {
            return Result.fail("failed to login");
        }
        return Result.success(jwt);
    }

}
