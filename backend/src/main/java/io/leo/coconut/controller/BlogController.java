package io.leo.coconut.controller;

import io.leo.coconut.util.JwtUtil;
import io.leo.coconut.common.Result;
import io.leo.coconut.model.dto.BlogDto;
import io.leo.coconut.model.entity.Blog;
import io.leo.coconut.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Leo
 */
@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @PostMapping("edit")
    public Result edit(@RequestHeader(value = "Authorization") String jwt, @Valid @RequestBody BlogDto blogDto) {
        Blog blog = blogService.edit(blogDto, JwtUtil.getUserId(jwt));
        return blog == null ? Result.fail("failed to edit") : Result.success(blog);
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@RequestHeader(value = "Authorization") String jwt, @PathVariable("id") Integer blogId) {
        return blogService.delete(blogId, JwtUtil.getUserId(jwt)) ? Result.success(null) : Result.fail("failed to delete");
    }

    @GetMapping("{id}")
    public Result view(@PathVariable("id") Integer blogId) {
        return Result.success(blogService.view(blogId));
    }

    @GetMapping("list")
    public Result list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return Result.success(blogService.list(pageNum, pageSize));
    }

}
