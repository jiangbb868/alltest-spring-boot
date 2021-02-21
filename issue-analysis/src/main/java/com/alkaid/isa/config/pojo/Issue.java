package com.alkaid.isa.config.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Issue {
    @TableId("id")
    private long id;
    private String name;
}
