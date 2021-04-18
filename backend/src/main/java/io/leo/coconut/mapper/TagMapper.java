package io.leo.coconut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.leo.coconut.model.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Leo
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * Get all the tag names of a blog.
     * @param blogId
     * @return tag names
     */
    List<String> getTagNamesByBlogId(@Param("blog_id") Integer blogId);

}
