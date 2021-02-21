package com.alkaid.clickhouse.service.impl;

import com.alkaid.clickhouse.entity.Visits;
import com.alkaid.clickhouse.mapper.VisitsMapper;
import com.alkaid.clickhouse.service.IVisitsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VisitsServiceImpl extends ServiceImpl<VisitsMapper, Visits>  implements IVisitsService {

}
