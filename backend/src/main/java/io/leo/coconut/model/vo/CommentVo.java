package io.leo.coconut.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author Leo
 */
@Data
@AllArgsConstructor
public class CommentVo {

    private String content;

    private String username;

    private Date createTime;

}
