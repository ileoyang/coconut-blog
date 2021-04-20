package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.mapper.CommentMapper;
import io.leo.coconut.model.dto.CommentDto;
import io.leo.coconut.model.entity.Comment;
import io.leo.coconut.model.entity.User;
import io.leo.coconut.model.vo.CommentVo;
import io.leo.coconut.service.CommentService;
import io.leo.coconut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    UserService userService;

    @Override
    public List<CommentVo> getCommentVoByBlogId(Integer blogId) {
        List<Comment> comments = list(new LambdaQueryWrapper<Comment>().eq(Comment::getBlogId, blogId).orderByDesc(Comment::getCreateTime));
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            User user = userService.getById(comment.getUserId());
            commentVos.add(new CommentVo(comment.getContent(), user.getUsername(), comment.getCreateTime()));
        }
        return commentVos;
    }

    @Override
    public void add(CommentDto commentDto, Integer userId) {
        save(Comment.builder().content(commentDto.getContent()).blogId(commentDto.getBlogId()).userId(userId).build());
    }

}
