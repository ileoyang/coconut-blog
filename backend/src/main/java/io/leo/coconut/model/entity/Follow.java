package io.leo.coconut.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Leo
 */
@Data
@AllArgsConstructor
public class Follow {

    private Integer leaderId;

    private Integer followerId;

}
