package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.mapper.FollowMapper;
import io.leo.coconut.model.entity.Follow;
import io.leo.coconut.service.FollowService;
import org.springframework.stereotype.Service;

/**
 * @author Leo
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Override
    public Boolean isFollow(Integer leaderId, Integer followerId) {
        return count(new LambdaQueryWrapper<Follow>().eq(Follow::getLeaderId, leaderId).eq(Follow::getFollowerId, followerId)) == 1;
    }

    @Override
    public String xorFollow(Integer leaderId, Integer followerId) {
        if (isFollow(leaderId, followerId)) {
            remove(new LambdaQueryWrapper<Follow>().eq(Follow::getLeaderId, leaderId).eq(Follow::getFollowerId, followerId));
            return "successfully unfollowed";
        }
        save(new Follow(leaderId, followerId));
        return "successfully followed";
    }

    @Override
    public Integer countFollower(Integer leaderId) {
        return count(new LambdaQueryWrapper<Follow>().eq(Follow::getLeaderId, leaderId));
    }

}
