package com.alkaid.isa.config.service;

import com.alkaid.isa.config.api.IIssueService;
import com.alkaid.isa.config.mapper.IssueMapper;
import com.alkaid.isa.config.pojo.Issue;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue>
        implements IIssueService {

}
