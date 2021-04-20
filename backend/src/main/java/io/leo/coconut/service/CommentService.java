package io.leo.coconut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.leo.coconut.model.dto.CommentDto;
import io.leo.coconut.model.entity.Comment;
import io.leo.coconut.model.vo.CommentVo;

import java.util.List;

/**
 * @author Leo
 */
public interface CommentService extends IService<Comment> {

    /**
     * Get all the comments of a blog.
     * @param blogId
     * @return comment view objects
     */
    List<CommentVo> getCommentVoByBlogId(Integer blogId);

    /**
     * Add a comment.
     * @param commentDto
     * @param userId
     */
    void add(CommentDto commentDto, Integer userId);

}
