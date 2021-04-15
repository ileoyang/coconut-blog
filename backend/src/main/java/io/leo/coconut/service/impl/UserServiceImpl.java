package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.common.JwtUtil;
import io.leo.coconut.mapper.UserMapper;
import io.leo.coconut.model.dto.LoginDto;
import io.leo.coconut.model.dto.RegisterDto;
import io.leo.coconut.model.entity.User;
import io.leo.coconut.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author Leo
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User register(RegisterDto registerDto) {
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, registerDto.getUsername()));
        if (count == 1) {
            return null;
        }
        User user = User.builder()
                .username(registerDto.getUsername())
                .password(DigestUtils.md5DigestAsHex(registerDto.getPassword().getBytes()))
                .email(registerDto.getEmail())
                .build();
        baseMapper.insert(user);
        return user;
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginDto.getUsername()));
        if (ObjectUtils.isEmpty(user) || !user.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))) {
            return null;
        }
        return JwtUtil.getToken(user.getId());
    }

}
