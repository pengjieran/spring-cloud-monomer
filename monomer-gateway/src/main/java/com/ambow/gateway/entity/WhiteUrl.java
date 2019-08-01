package com.ambow.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aaron
 * @since 2019-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_white_url")
public class WhiteUrl implements Serializable {

    private static final long  serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long               id;

    @TableField("url")
    private String             url;

    public static final String ID               = "id";

    public static final String URL              = "url";

}
