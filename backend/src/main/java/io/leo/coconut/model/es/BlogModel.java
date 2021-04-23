package io.leo.coconut.model.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Leo
 */
@Document(indexName = "blog_model")
@Data
public class BlogModel {

    @Id
    private String id;

    private Integer blogId;

    private String content;

}
