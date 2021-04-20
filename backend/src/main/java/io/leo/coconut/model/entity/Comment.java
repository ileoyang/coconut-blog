package io.leo.coconut.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Leo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    private Integer blogId;

    private Integer userId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
