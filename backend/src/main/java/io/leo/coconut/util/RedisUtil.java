package io.leo.coconut.util;

import io.leo.coconut.model.entity.Blog;
import io.leo.coconut.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Leo
 */
@Component
public class RedisUtil {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BlogService blogService;

    public Integer getView(String blogId) {
        if(redisTemplate.hasKey(blogId)) return Integer.valueOf(redisTemplate.opsForValue().get(blogId).toString());
        return 0;
    }

    public Integer incrView(String blogId) {
        redisTemplate.opsForSet().add("viewBlogIds", blogId);
        return (int) (long) redisTemplate.opsForValue().increment(blogId, 1);
    }

    public void syncView() {
        Set<String> blogIds = redisTemplate.opsForSet().members("viewBlogIds");
        for(String blogId : blogIds) {
            Blog blog = blogService.getById(blogId);
            blog.setView(blog.getView() + getView(blogId));
            blogService.updateById(blog);
            redisTemplate.delete(blogId);
        }
        redisTemplate.delete("viewBlogIds");
    }

}
