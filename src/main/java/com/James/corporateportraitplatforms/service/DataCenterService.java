package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.mapper.CompanyExtendMapper;
import com.James.corporateportraitplatforms.mapper.TagCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            count1 = companyExtendMapper.selectByScaleAndCidList("1", cidList).size();
            count2 = companyExtendMapper.selectByScaleAndCidList("0", cidList).size();
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
        IndustryList.add("零售业");
        IndustryList.add("工业");
        IndustryList.add("交通运输业");
        int count1,count2;
        List<String> nullList = new ArrayList<>();
        nullList.add("null");
        for(int i = 0 ; i < 5 ; i++){
            count1 = companyExtendMapper.selectByIndustry(IndustryList.get(i),nullList,nullList,nullList).size();
            count2 = companyExtendMapper.selectByIndustry(IndustryList.get(i),nullList,nullList,nullList).size();
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
}
