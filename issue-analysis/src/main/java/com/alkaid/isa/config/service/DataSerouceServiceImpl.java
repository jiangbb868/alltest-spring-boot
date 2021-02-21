package com.alkaid.isa.config.service;

import com.alkaid.isa.config.api.IDataSourceService;
import com.alkaid.isa.config.mapper.DataSourceMapper;
import com.alkaid.isa.config.pojo.DataSource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSerouceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource> implements IDataSourceService {

}
