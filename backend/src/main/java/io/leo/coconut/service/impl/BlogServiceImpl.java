package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.mapper.BlogMapper;
import io.leo.coconut.model.dto.BlogDto;
import io.leo.coconut.model.entity.Blog;
import io.leo.coconut.model.entity.BlogTag;
import io.leo.coconut.model.vo.BlogVo;
import io.leo.coconut.service.BlogService;
import io.leo.coconut.service.BlogTagService;
import io.leo.coconut.service.TagService;
import io.leo.coconut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Leo
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    UserService userService;

    @Autowired
    TagService tagService;

    @Autowired
    BlogTagService blogTagService;

    @Override
    public Blog edit(BlogDto blogDto, Integer userId) {
        Blog blog = new Blog();
        if (blogDto.getBlogId() != null) {
            blog = baseMapper.selectById(blogDto.getBlogId());
            if (!userId.equals(blog.getUserId())) {
                return null;
            }
            blogTagService.remove(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getBlogId, blogDto.getBlogId()));
        } else {
            blog.setUserId(userId);
        }
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        saveOrUpdate(blog);
        for (String tagName : blogDto.getTagNames()) {
            Integer tagId = tagService.add(tagName);
            blogTagService.save(new BlogTag(blog.getId(), tagId));
        }
        return blog;
    }

    @Override
    public Boolean delete(Integer blogId, Integer userId) {
        Blog blog = baseMapper.selectById(blogId);
        if (!userId.equals(blog.getUserId())) {
            return false;
        }
        baseMapper.deleteById(blogId);
        blogTagService.remove(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getBlogId, blogId));
        return true;
    }

    @Override
    public BlogVo view(Integer blogId) {
        Blog blog = baseMapper.selectById(blogId);
        blog.setView(blog.getView() + 1);
        baseMapper.updateById(blog);
        return getBlogVo(blog);
    }

    @Override
    public IPage<BlogVo> list(int pageNum, int pageSize) {
        IPage<Blog> blogIPage = page(new Page(pageNum, pageSize), new LambdaQueryWrapper<Blog>().orderByDesc(Blog::getCreateTime));
        IPage<BlogVo> blogVoIPage = new Page<>(blogIPage.getCurrent(), blogIPage.getSize(), blogIPage.getTotal());
        ArrayList<BlogVo> records = new ArrayList<>();
        for (Blog blog : blogIPage.getRecords()) {
            records.add(getBlogVo(blog));
        }
        blogVoIPage.setRecords(records);
        return blogVoIPage;
    }

    private BlogVo getBlogVo(Blog blog) {
        return new BlogVo(blog.getId(), blog.getTitle(), blog.getContent(), blog.getUserId(), userService.getById(blog.getUserId()).getUsername(),
                blog.getView(), tagService.getTagNamesByBlogId(blog.getId()), blog.getCreateTime());
    }

}
