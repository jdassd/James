package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.mapper.CompanyExtendMapper;
import com.James.corporateportraitplatforms.mapper.TagCompanyMapper;
import com.James.corporateportraitplatforms.utils.CharactersUtils;
import com.James.corporateportraitplatforms.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweeneyhe.bean.YearBean;

import java.util.*;

@Service
public class DataCenterService {

    @Autowired
    private CompanyExtendMapper companyExtendMapper;

    @Autowired
    private TagCompanyMapper tagCompanyMapper;

    /**
     * 获取按省份的维度排序表
     * @return
     */
    public List<Map<String,Object>> getProvinceRankList(){
        ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<String> cityList = companyExtendMapper.selectCompanyCity();
        int count1,count2;
        for(String city : cityList){
            count1 = companyExtendMapper.getCountByProvinceAndFlag(city, "1");
            count2 = companyExtendMapper.getCountByProvinceAndFlag(city, "0");
            HashMap<String, Object> hashMap = new HashMap<>();
            if("".equals(city)){
                hashMap.put("key","未知");
            }else{
                hashMap.put("key",city);
            }
            hashMap.put("yes",""+count1);
            hashMap.put("not",""+count2);
            if((count1+count2) == 0){
                continue;
                //hashMap.put("hot",0);
            }else{
                hashMap.put("hot",(count1*5/(count1+count2)));
            }
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 获取按规模的维度排序表
     * @return
     */
    public List<Map<String,Object>> getScaleRankList(){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        List<String> scaleList = new ArrayList<>();
        scaleList.add("大型企业");
        scaleList.add("中型企业");
        scaleList.add("小型企业");
        scaleList.add("微型企业");
        int count1,count2;
        for(int i = 0 ; i < 4 ; i++){
            List<String> cidList = tagCompanyMapper.selectCidListByTid(34+i);
            if(cidList.size() == 0){
                cidList.add("null");
            }
            count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("key",scaleList.get(i));
            hashMap.put("yes",""+count1);
            hashMap.put("not",""+count2);
            if((count1+count2) == 0){
                continue;
                //hashMap.put("hot",0);
            }else{
                hashMap.put("hot",(count1*5/(count1+count2)));
            }
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 获取按行业的维度排序表
     * @return
     */
    public List<Map<String,Object>> getIndustryRankList(){
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        List<String> IndustryList = new ArrayList<>();
        IndustryList.add("服务业");
        IndustryList.add("制造业");
        IndustryList.add("工业");
        IndustryList.add("交通运输业");
        IndustryList.add("零售业");
        int count1,count2;
        for(int i = 0 ; i < 5 ; i++){
            List<String> cidList = tagCompanyMapper.selectCidListByTid(i);
            if(cidList.size() == 0){
                cidList.add("null");
            }
            count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("key",IndustryList.get(i));
            hashMap.put("yes",""+count1);
            hashMap.put("not",""+count2);
            if((count1+count2) == 0){
                continue;
                //hashMap.put("hot",0);
            }else{
                hashMap.put("hot",(count1*5/(count1+count2)));
            }
            list.add(hashMap);
        }
        return list;
    }

    /**
     * 获取僵尸企业和非僵尸企业数量
     * @return
     */
    public Map<String,Object> getNumWithFlag(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("isNum",companyExtendMapper.selectByFlag("1").size());
        hashMap.put("notNum",companyExtendMapper.selectByFlag("0").size());
        return hashMap;
    }

    /**
     * 获取行业分布表
     * @return
     */
    public List<Object> getIndustryPicList(){
        ArrayList<Object> list = new ArrayList<>();
        List<String> IndustryList = new ArrayList<>();
        IndustryList.add("服务业");
        IndustryList.add("制造业");
        IndustryList.add("工业");
        IndustryList.add("交通运输业");
        IndustryList.add("零售业");
        int count1,count2;
        int[][] num = new int[2][5];
        for(int i = 0 ; i < 5 ; i++){
            List<String> cidList = tagCompanyMapper.selectCidListByTid(i);
            if(cidList.size() == 0){
                cidList.add("null");
            }
            count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            num[0][i] = count1;
            num[1][i] = count2;
        }
        list.add(num[0]);
        list.add(num[1]);
        return list;
    }

    /**
     * 获取规模分布表
     * @return
     */
    public List<Object> getScalePicList(){
        ArrayList<Object> list = new ArrayList<>();
        List<String> scaleList = new ArrayList<>();
        scaleList.add("大型企业");
        scaleList.add("中型企业");
        scaleList.add("小型企业");
        scaleList.add("微型企业");
        int count1,count2;
        int[][] num = new int[2][4];
        for(int i = 0 ; i < 4 ; i++){
            List<String> cidList = tagCompanyMapper.selectCidListByTid(34+i);
            if(cidList.size() == 0){
                cidList.add("null");
            }
            count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            num[0][i] = count1;
            num[1][i] = count2;
        }
        list.add(num[0]);
        list.add(num[1]);
        return list;
    }

    /**
     * 获取盈利分布表
     * @return
     */
    public Map<String,Object> getProfitPicMap(){
        HashMap<String,Object> map = new HashMap<>();
        double[][] count = new double[4][3];
        List<YearBean> yearBeanList = CsvUtils.yearBeanList;
        Map<Integer, Integer> flags = CsvUtils.flagsMap;
        for(int i = 2015 ; i < 2018 ; i++){
            for(YearBean yearBean : yearBeanList){
                //判断企业年份
                if(yearBean.year() == i) {
                    //判断是否僵尸企业
                    double profit = yearBean.net_profit();
                    if (flags.get(yearBean.id()) == 1) {
                        if (profit >= 0.0) {
                            count[0][i-2015] += profit;
                        } else {
                            count[2][i-2015] += profit;
                        }
                    } else {
                        if (profit >= 0.0) {
                            count[1][i-2015] += profit;
                        } else {
                            count[3][i-2015] += profit;
                        }
                    }
                }
            }
        }
        map.put("profitData",count);
        return map;
    }
}
