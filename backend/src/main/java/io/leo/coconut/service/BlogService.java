package io.leo.coconut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.leo.coconut.model.dto.BlogDto;
import io.leo.coconut.model.entity.Blog;
import io.leo.coconut.model.vo.BlogVo;

/**
 * @author Leo
 */
public interface BlogService extends IService<Blog>  {

    /**
     * Modify a blog if provides blogId else create one.
     * @param blogDto
     * @param userId
     * @return new blog object if success else null
     */
    Blog edit(BlogDto blogDto, Integer userId);

    /**
     * Delete a blog.
     * @param blogId
     * @param userId
     * @return true if success
     */
    Boolean delete(Integer blogId, Integer userId);

    /**
     * View a blog.
     * @param blogId
     * @return blog view object
     */
    BlogVo view(Integer blogId);

    /**
     * Get a list of blogs.
     * @param pageNum the number of page
     * @param pageSize the size of each page
     * @return blog view objects with pagination information
     */
    IPage<BlogVo> list(int pageNum, int pageSize);

    /**
     * Count the number of blogs of a user.
     * @param userId
     * @return the number of blogs
     */
    Integer countBlog(Integer userId);

}
