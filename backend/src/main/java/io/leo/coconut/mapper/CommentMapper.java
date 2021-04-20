package io.leo.coconut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.leo.coconut.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Leo
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
