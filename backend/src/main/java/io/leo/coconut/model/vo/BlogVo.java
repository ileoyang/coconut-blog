package io.leo.coconut.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Leo
 */
@Data
@AllArgsConstructor
public class BlogVo {

    private Integer blogId;

    private String title;

    private String content;

    private Integer userId;

    private String username;

    private Integer view;

    private List<String> tagNames;

    private Date createTime;

}
