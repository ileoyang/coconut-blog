package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.mapper.TagMapper;
import io.leo.coconut.model.entity.Tag;
import io.leo.coconut.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leo
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public Integer add(String tagName) {
        Tag tag = baseMapper.selectOne(new LambdaQueryWrapper<Tag>().eq(Tag::getName, tagName));
        if (tag == null) {
            tag = Tag.builder().name(tagName).build();
            baseMapper.insert(tag);
        }
        return tag.getId();
    }

    @Override
    public List<String> getTagNamesByBlogId(Integer blogId) {
        return baseMapper.getTagNamesByBlogId(blogId);
    }

}
