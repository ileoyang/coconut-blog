package io.leo.coconut.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Leo
 */
@Data
public class BlogDto {

    private Integer blogId;

    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotBlank(message = "content cannot be blank")
    private String content;

    @NotEmpty(message = "tag names cannot be empty")
    private List<String> tagNames;

}
