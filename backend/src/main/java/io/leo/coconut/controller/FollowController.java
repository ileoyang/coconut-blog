package io.leo.coconut.controller;

import io.leo.coconut.util.JwtUtil;
import io.leo.coconut.common.Result;
import io.leo.coconut.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leo
 */
@RestController
@RequestMapping("follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping("is/{id}")
    public Result isFollow(@RequestHeader(value = "Authorization") String jwt, @PathVariable("id") Integer leaderId) {
        return Result.success(followService.isFollow(leaderId, JwtUtil.getUserId(jwt)));
    }

    @GetMapping("{id}")
    public Result xorFollow(@RequestHeader(value = "Authorization") String jwt, @PathVariable("id") Integer leaderId) {
        Integer followerId = JwtUtil.getUserId(jwt);
        return leaderId.equals(followerId) ? Result.fail("cannot follow yourself") : Result.success(followService.xorFollow(leaderId, followerId));
    }

}
