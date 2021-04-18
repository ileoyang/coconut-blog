package io.leo.coconut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.leo.coconut.model.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Leo
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}
