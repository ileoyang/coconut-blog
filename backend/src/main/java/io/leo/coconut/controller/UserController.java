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
        return user == null ? Result.fail("failed to register") : Result.success(user);
    }

    @PostMapping("login")
    public Result login(@Valid @RequestBody LoginDto loginDto) {
        String jwt = userService.login(loginDto);
        return jwt == null ? Result.fail("failed to login") : Result.success(jwt);
    }

    @GetMapping("{id}")
    public Result getUserVo(@PathVariable("id") Integer userId) {
        return Result.success(userService.getUserVo(userId));
    }

}
