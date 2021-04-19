package io.leo.coconut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.leo.coconut.model.entity.Follow;

/**
 * @author Leo
 */
public interface FollowService extends IService<Follow> {

    /**
     * Check if a user follows another.
     * @param leaderId ID of candidate user who is followed
     * @param followerId ID of candidate user who follows
     * @return true if follows
     */
    Boolean isFollow(Integer leaderId, Integer followerId);

    /**
     * Follow if not followed else unfollow.
     * @param leaderId ID of user who is followed
     * @param followerId ID of user who follows
     * @return specific operation message
     */
    String xorFollow(Integer leaderId, Integer followerId);

    /**
     * Count the number of followers of a user.
     * @param leaderId
     * @return the number of followers
     */
    Integer countFollower(Integer leaderId);

}
