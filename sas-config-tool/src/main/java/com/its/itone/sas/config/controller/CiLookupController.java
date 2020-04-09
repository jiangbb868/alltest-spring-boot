package com.its.itone.sas.config.controller;

import com.its.itone.sas.config.api.CiLookupService;
import com.its.itone.sas.config.dao.pojo.CiLookup;
import com.its.itone.sas.config.vo.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/sas/lookup")
@RestController
public class CiLookupController {

    @Autowired
    private CiLookupService ciLookupService;

    @GetMapping(value="/list")
    public JsonResult<CiLookup> listByLookupType(String lookupType) {
        List<CiLookup> lookupList = ciLookupService.listByCiLookupType(lookupType);
        return JsonResult.success(lookupList);
    }

}
