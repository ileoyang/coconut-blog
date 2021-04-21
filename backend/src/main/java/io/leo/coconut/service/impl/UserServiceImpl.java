package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.util.JwtUtil;
import io.leo.coconut.mapper.UserMapper;
import io.leo.coconut.model.dto.LoginDto;
import io.leo.coconut.model.dto.RegisterDto;
import io.leo.coconut.model.entity.User;
import io.leo.coconut.model.vo.UserVo;
import io.leo.coconut.service.BlogService;
import io.leo.coconut.service.FollowService;
import io.leo.coconut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author Leo
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    BlogService blogService;

    @Autowired
    FollowService followService;

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
        if (user == null || !user.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()))) {
            return null;
        }
        return JwtUtil.getToken(user.getId());
    }

    @Override
    public UserVo getUserVo(Integer userId) {
        return new UserVo(blogService.countBlog(userId), followService.countFollower(userId));
    }

}
