package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import com.James.corporateportraitplatforms.service.DataCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data-center")
public class DataCenterController {

    @Autowired
    private DataCenterService dataCenterService;

    /**
     * 获取省份维度排序表
     * @return
     */
    @GetMapping("/getProvinceRank.do")
    public AjaxResponseModel<List<Map<String,Object>>> getProvinceRank(){
        List<Map<String, Object>> provinceRankList = dataCenterService.getProvinceRankList();
        return AjaxResponseModel.<List<Map<String,Object>>>builder()
                .code(0)
                .data(provinceRankList)
                .msg("getProvinceRank.do successfully").build();
    }

    /**
     * 获取规模维度排序表
     * @return
     */
    @GetMapping("/getScaleRank.do")
    public AjaxResponseModel<List<Map<String,Object>>> getScaleRank(){
        List<Map<String, Object>> scaleRankList = dataCenterService.getScaleRankList();
        return AjaxResponseModel.<List<Map<String,Object>>>builder()
                .code(0)
                .data(scaleRankList)
                .msg("getScaleRank.do successfully").build();
    }

    @GetMapping("/getIndustryRank.do")
    public AjaxResponseModel<List<Map<String,Object>>> getIndustryRank(){
        List<Map<String, Object>> getIndustryRankList = dataCenterService.getIndustryRankList();
        return AjaxResponseModel.<List<Map<String,Object>>>builder()
                .code(0)
                .data(getIndustryRankList)
                .msg("getScaleRank.do successfully").build();
    }
}
