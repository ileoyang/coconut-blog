package io.leo.coconut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.leo.coconut.model.entity.Tag;

import java.util.List;

/**
 * @author Leo
 */
public interface TagService extends IService<Tag> {

    /**
     * Add a tag if not exist.
     * @param tagName
     * @return tag ID
     */
    Integer add(String tagName);

    /**
     * Get all the tag names of a blog.
     * @param blogId
     * @return tag names
     */
    List<String> getTagNamesByBlogId(Integer blogId);

}
