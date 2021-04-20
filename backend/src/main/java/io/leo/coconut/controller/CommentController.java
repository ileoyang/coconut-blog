package io.leo.coconut.controller;

import io.leo.coconut.common.JwtUtil;
import io.leo.coconut.common.Result;
import io.leo.coconut.model.dto.CommentDto;
import io.leo.coconut.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Leo
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("blog/{id}")
    public Result getCommentVosByBlogId(@PathVariable("id") Integer blogId) {
        return Result.success(commentService.getCommentVoByBlogId(blogId));
    }

    @PostMapping("add")
    public Result add(@RequestHeader(value = "Authorization") String jwt, @Valid @RequestBody CommentDto commentDto) {
        commentService.add(commentDto, JwtUtil.getUserId(jwt));
        return Result.success(null);
    }

}
