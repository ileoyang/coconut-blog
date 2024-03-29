package io.leo.coconut.util;

import io.leo.coconut.model.entity.Blog;
import io.leo.coconut.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author Leo
 */
@Component
public class RedisUtil {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BlogService blogService;

    private static Semaphore semaphore = new Semaphore(1);

    public Integer getView(String blogId) {
        return redisTemplate.hasKey(blogId) ? Integer.valueOf(redisTemplate.opsForValue().get(blogId).toString()) : 0;
    }

    public Integer incrView(String blogId) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForSet().add("viewBlogIds", blogId);
        Integer deltaView = (int) (long) redisTemplate.opsForValue().increment(blogId, 1);
        semaphore.release();
        return deltaView;
    }

    public void syncView() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> blogIds = redisTemplate.opsForSet().members("viewBlogIds");
        for (String blogId : blogIds) {
            Blog blog = blogService.getById(blogId);
            blog.setView(blog.getView() + getView(blogId));
            blogService.updateById(blog);
            redisTemplate.delete(blogId);
        }
        redisTemplate.delete("viewBlogIds");
        semaphore.release();
    }

}
