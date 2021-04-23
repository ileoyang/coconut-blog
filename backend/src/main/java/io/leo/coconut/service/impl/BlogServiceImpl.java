package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.mapper.BlogMapper;
import io.leo.coconut.model.dto.BlogDto;
import io.leo.coconut.model.entity.Blog;
import io.leo.coconut.model.entity.BlogTag;
import io.leo.coconut.model.es.BlogModel;
import io.leo.coconut.model.vo.BlogVo;
import io.leo.coconut.repository.BlogModelRepository;
import io.leo.coconut.service.BlogService;
import io.leo.coconut.service.BlogTagService;
import io.leo.coconut.service.TagService;
import io.leo.coconut.service.UserService;
import io.leo.coconut.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    BlogModelRepository blogModelRepository;

    @Override
    public Blog edit(BlogDto blogDto, Integer userId) {
        Blog blog = new Blog();
        BlogModel blogModel = new BlogModel();
        if (blogDto.getBlogId() != null) {
            blog = baseMapper.selectById(blogDto.getBlogId());
            if (!userId.equals(blog.getUserId())) {
                return null;
            }
            blogTagService.remove(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getBlogId, blogDto.getBlogId()));
            blogModel = blogModelRepository.findByBlogId(blog.getId());
        } else {
            blog.setUserId(userId);
        }
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        saveOrUpdate(blog);
        blogModel.setBlogId(blog.getId());
        blogModel.setContent(blog.getContent());
        blogModelRepository.save(blogModel);
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
        BlogModel blogModel = blogModelRepository.findByBlogId(blogId);
        blogModelRepository.delete(blogModel);
        return true;
    }

    @Override
    public BlogVo view(Integer blogId) {
        Blog blog = baseMapper.selectById(blogId);
        Integer incr = redisUtil.incrView(String.valueOf(blogId));
        blog.setView(blog.getView() + incr);
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

    @Override
    public Integer countBlog(Integer userId) {
        return count(new LambdaQueryWrapper<Blog>().eq(Blog::getUserId, userId));
    }

    @Override
    public IPage<BlogVo> list(String word, int pageNum, int pageSize) {
        List<BlogModel> blogModels = blogModelRepository.findByContentIsContaining(word);
        IPage<BlogVo> blogVoIPage = new Page<>(pageNum, pageSize, blogModels.size());
        ArrayList<BlogVo> records = new ArrayList<>();
        for (BlogModel blogModel : blogModels) {
            records.add(getBlogVo(blogModel));
        }
        blogVoIPage.setRecords(records);
        return blogVoIPage;
    }

    private BlogVo getBlogVo(Blog blog) {
        return new BlogVo(blog.getId(), blog.getTitle(), blog.getContent(), blog.getUserId(), userService.getById(blog.getUserId()).getUsername(),
                blog.getView(), tagService.getTagNamesByBlogId(blog.getId()), blog.getCreateTime());
    }

    private BlogVo getBlogVo(BlogModel blogModel) {
        Blog blog = getById(blogModel.getBlogId());
        return getBlogVo(blog);
    }

}
