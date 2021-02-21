package com.alkaid.isa.flow.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AnalysisSequence {
    @TableId("id")
    private long id;
    private long analysisFlow;
    private long preNode;
    private long nextNode;
}
