package io.leo.coconut.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Leo
 */
@Data
@AllArgsConstructor
public class BlogTag {

    private Integer blogId;

    private Integer tagId;

}
