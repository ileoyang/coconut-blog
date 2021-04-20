package io.leo.coconut.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Leo
 */
@Data
public class CommentDto {

    @NotBlank(message = "content cannot be blank")
    private String content;

    @NotNull(message = "blog ID cannot be null")
    private Integer blogId;

}
