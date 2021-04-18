package io.leo.coconut.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

/**
 * @author Leo
 */
@Data
@Builder
public class Tag {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

}
