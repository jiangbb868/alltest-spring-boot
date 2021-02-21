package com.alkaid.isa.config.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AnalysisProcess {
    @TableId("id")
    private long id;
    private String name;
    private String type;
    private String content;
    private String inParams;
    private String outParams;
    private String describe;
}
