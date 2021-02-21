package com.alkaid.helm.service;

import com.alkaid.helm.dao.mapper.TblUserMapper;
import com.alkaid.helm.dao.pojo.TblUser;
import com.alkaid.helm.dao.pojo.TblUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private TblUserMapper mapper;

    public List<TblUser> list() {
        TblUserExample example = new TblUserExample();
        return mapper.selectByExample(example);
    }
}
