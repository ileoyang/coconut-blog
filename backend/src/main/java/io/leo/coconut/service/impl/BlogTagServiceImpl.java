package io.leo.coconut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.leo.coconut.mapper.BlogTagMapper;
import io.leo.coconut.model.entity.BlogTag;
import io.leo.coconut.service.BlogTagService;
import org.springframework.stereotype.Service;

/**
 * @author Leo
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

}
