package com.alkaid.isa.flow.service;

import com.alkaid.isa.flow.api.IAnalysisSequenceService;
import com.alkaid.isa.flow.mapper.AnalysisSequenceMapper;
import com.alkaid.isa.flow.pojo.AnalysisSequence;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AnalysisSequenceServiceImpl extends ServiceImpl<AnalysisSequenceMapper, AnalysisSequence>
        implements IAnalysisSequenceService {

}
