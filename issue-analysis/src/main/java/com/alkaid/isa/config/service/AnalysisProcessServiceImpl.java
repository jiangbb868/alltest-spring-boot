package com.alkaid.isa.config.service;

import com.alkaid.isa.config.api.IAnalysisProcessService;
import com.alkaid.isa.config.api.IDataSourceService;
import com.alkaid.isa.config.mapper.AnalysisProcessMapper;
import com.alkaid.isa.config.pojo.AnalysisProcess;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AnalysisProcessServiceImpl extends ServiceImpl<AnalysisProcessMapper, AnalysisProcess>
        implements IAnalysisProcessService {

}
