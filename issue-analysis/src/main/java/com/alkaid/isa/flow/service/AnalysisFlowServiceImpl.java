package com.alkaid.isa.flow.service;

import com.alkaid.isa.flow.api.IAnalysisFlowService;
import com.alkaid.isa.flow.mapper.AnalysisFlowMapper;
import com.alkaid.isa.flow.pojo.AnalysisFlow;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AnalysisFlowServiceImpl extends ServiceImpl<AnalysisFlowMapper, AnalysisFlow>
        implements IAnalysisFlowService {

}
