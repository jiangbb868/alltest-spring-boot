package com.alkaid.isa.flow.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AnalysisFlow {
    @TableId("id")
    private long id;
    private String name;
    private long object;
    private long firstSequence;
}
