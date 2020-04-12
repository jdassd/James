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
        long start = System.currentTimeMillis();
        List<Map<String, Object>> provinceRankList = dataCenterService.getProvinceRankList();
        return AjaxResponseModel.<List<Map<String,Object>>>builder()
                .code(0)
                .data(provinceRankList)
                .msg("getProvinceRank.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }

    /**
     * 获取规模维度排序表
     * @return
     */
    @GetMapping("/getScaleRank.do")
    public AjaxResponseModel<List<Map<String,Object>>> getScaleRank(){
        long start = System.currentTimeMillis();
        List<Map<String, Object>> scaleRankList = dataCenterService.getScaleRankList();
        return AjaxResponseModel.<List<Map<String,Object>>>builder()
                .code(0)
                .data(scaleRankList)
                .msg("getScaleRank.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }

    /**
     * 获取行业维度排序表
     * @return
     */
    @GetMapping("/getIndustryRank.do")
    public AjaxResponseModel<List<Map<String,Object>>> getIndustryRank(){
        long start = System.currentTimeMillis();
        List<Map<String, Object>> getIndustryRankList = dataCenterService.getIndustryRankList();
        return AjaxResponseModel.<List<Map<String,Object>>>builder()
                .code(0)
                .data(getIndustryRankList)
                .msg("getScaleRank.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }

    /**
     * 获取僵尸企业和非僵尸企业数量
     * @return
     */
    @GetMapping("/getNumWithFlag.do")
    public AjaxResponseModel<Map<String,Object>> getNumWithFlag(){
        long start = System.currentTimeMillis();
        Map<String, Object> numWithFlag = dataCenterService.getNumWithFlag();
        return AjaxResponseModel.<Map<String,Object>>builder()
                .code(0)
                .data(numWithFlag)
                .msg("getNumWithFlag.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }

    /**
     * 获取行业分布表
     * @return
     */
    @GetMapping("/getIndustryPic.do")
    public AjaxResponseModel<List<Object>> getIndustryPic(){
        long start = System.currentTimeMillis();
        List<Object> industryPic = dataCenterService.getIndustryPicList();
        return AjaxResponseModel.<List<Object>>builder()
                .code(0)
                .data(industryPic)
                .msg("getIndustryPic.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }

    /**
     * 获取规模分布表
     * @return
     */
    @GetMapping("/getScalePic.do")
    public AjaxResponseModel<List<Object>> getScalePic(){
        long start = System.currentTimeMillis();
        List<Object> scalePic = dataCenterService.getScalePicList();
        return AjaxResponseModel.<List<Object>>builder()
                .code(0)
                .data(scalePic)
                .msg("getScalePic.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }

    /**
     * 获取盈利分布表
     * @return
     */
    @GetMapping("/getProfitPic.do")
    public AjaxResponseModel<Map<String,Object>> getProfitPic(){
        long start = System.currentTimeMillis();
        Map<String,Object> profitPic = dataCenterService.getProfitPicMap();
        return AjaxResponseModel.<Map<String,Object>>builder()
                .code(0)
                .data(profitPic)
                .msg("getProfitPic.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }

    /**
     * 获取地图数据
     * @return
     */
    @GetMapping("/getMap.do")
    public AjaxResponseModel<Map<String,Object>> getMap(){
        long start = System.currentTimeMillis();
        Map<String,Object> map = dataCenterService.getMap();
        return AjaxResponseModel.<Map<String,Object>>builder()
                .code(0)
                .data(map)
                .msg("getMap.do successfully" + " 耗时：" +(System.currentTimeMillis() - start) + "毫秒").build();
    }
}
