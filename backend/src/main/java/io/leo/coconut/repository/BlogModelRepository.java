package io.leo.coconut.repository;

import io.leo.coconut.model.es.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leo
 */
@Repository
public interface BlogModelRepository extends ElasticsearchRepository<BlogModel, String> {

    /**
     * Find blog model by blog ID.
     * @param blogId
     * @return blog model
     */
    BlogModel findByBlogId(Integer blogId);

    /**
     * Find blog models whose content contains word.
     * @param word
     * @return blog models
     */
    List<BlogModel> findByContentIsContaining(String word);

}
