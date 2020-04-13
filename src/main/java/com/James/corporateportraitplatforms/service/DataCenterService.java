package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.mapper.CompanyExtendMapper;
import com.James.corporateportraitplatforms.mapper.TagCompanyMapper;
import com.James.corporateportraitplatforms.utils.CharactersUtils;
import com.James.corporateportraitplatforms.utils.CsvUtils;
import org.apache.spark.sql.sources.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweeneyhe.bean.ScoreBean;
import sweeneyhe.bean.ShowData;
import sweeneyhe.bean.YearBean;

import java.util.*;

@Service
public class DataCenterService {

    @Autowired
    private CompanyExtendMapper companyExtendMapper;

    @Autowired
    private TagCompanyMapper tagCompanyMapper;

    private static Map<String, Object> financialReportMap = null;

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
            //count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            //count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            count1 = companyExtendMapper.selectCountByFlagAndCidList("1",cidList);
            count2 = companyExtendMapper.selectCountByFlagAndCidList("0",cidList);
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
            //count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            //count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            count1 = companyExtendMapper.selectCountByFlagAndCidList("1",cidList);
            count2 = companyExtendMapper.selectCountByFlagAndCidList("0",cidList);
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
        //hashMap.put("isNum",companyExtendMapper.selectByFlag("1").size());
        //hashMap.put("notNum",companyExtendMapper.selectByFlag("0").size());
        hashMap.put("isNum",companyExtendMapper.selectCountByFlag("1"));
        hashMap.put("notNum",companyExtendMapper.selectCountByFlag("0"));
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
            //count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            //count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            count1 = companyExtendMapper.selectCountByFlagAndCidList("1",cidList);
            count2 = companyExtendMapper.selectCountByFlagAndCidList("0",cidList);
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
            //count1 = companyExtendMapper.selectByFlagAndCidList("1", cidList).size();
            //count2 = companyExtendMapper.selectByFlagAndCidList("0", cidList).size();
            count1 = companyExtendMapper.selectCountByFlagAndCidList("1",cidList);
            count2 = companyExtendMapper.selectCountByFlagAndCidList("0",cidList);
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
        for(YearBean yearBean : yearBeanList){
            //判断是否僵尸企业
            double profit = yearBean.net_profit();
            int year = yearBean.year();
            //System.out.println("yearBean id = " + yearBean.id());
            //if(flags.get(yearBean.id()) != null) {//避免数据清洗BUG
                if (flags.get(yearBean.id()) == 1) {
                    if (profit >= 0.0) {
                        count[0][year - 2015] += profit;
                    } else {
                        count[2][year - 2015] += profit;
                    }
                } else {
                    if (profit >= 0.0) {
                        count[1][year - 2015] += profit;
                    } else {
                        count[3][year - 2015] += profit;
                    }
                }
            //}
        }
        map.put("profitData",count);
        return map;
    }

    /**
     * 获取地图数据
     * @return
     */
    public Map<String, Object> getMap() {
        List<Map<String, Object>> provinceRankList = getProvinceRankList();
        List<Integer> valueList = new ArrayList<>();
        boolean flag;
        int max = 1;
        String[] provinces = new String[]{
                "湖北", "广东", "河南", "浙江", "湖南", "安徽", "江西", "江苏", "重庆", "山东", "四川", "黑龙江",
                "北京", "上海", "福建", "河北", "陕西", "广西", "海南", "云南", "贵州", "山西", "辽宁", "天津",
                "甘肃", "吉林", "宁夏", "新疆", "内蒙古", "香港", "台湾", "青海", "澳门", "西藏"
        };
        HashMap<String, Object> map = new HashMap<>();
        for(String province : provinces){
            flag = false;
            //查看是否有这个省的数据
            for(Map<String, Object> provinceRankMap : provinceRankList){
                if(province.equals(provinceRankMap.get("key"))){
                    int tempNum = Integer.parseInt(provinceRankMap.get("yes").toString());
                    if(tempNum >= max)
                        max = tempNum;
                    valueList.add(tempNum);
                    flag = true;
                    break;
                }
            }
            //如果没有这个省的数据
            if(!flag)
                valueList.add(0);
        }
        map.put("name",provinces);
        map.put("value",valueList);
        map.put("max",max);
        return map;
    }

    /**
     * 获取财报数据
     * @return
     */
    public static Map<String, Object> getFinancialReport(){
        if(financialReportMap == null){
            financialReportMap = new HashMap<>();
            new Thread(new Runnable(){
                @Override
                public void run() {
                    int isSumScore = 0,notSumScore = 0;
                    double[] isSumRoa = new double[3],isSumAssetLiabilityRatio = new double[3],notSumRoa = new double[3],notSumAssetLiabilityRatio = new double[3];
                    Map<Integer, Integer> flags = CsvUtils.flagsMap;
                    Map<Integer, List<ShowData>> showDataMap = CsvUtils.showDataMap;
                    List<ScoreBean> scoreBeanList = CsvUtils.scoreBeanList;
                    if(showDataMap.size() == 0){
                        return;
                    }
                    Map<String, Object> isMap = new HashMap<>();
                    Map<String, Object> notMap = new HashMap<>();
                    int isLength = 0,notLength = 0,index = 0;
                    for(ScoreBean scoreBean : scoreBeanList){
                        for(ShowData showData : showDataMap.get(scoreBean.id())){
                            switch (showData.year()){
                                case 2015:
                                    index = 0;
                                    break;
                                case 2016:
                                    index = 1;
                                    break;
                                case 2017:
                                    index = 2;
                                    break;
                            }
                            if(flags.get(scoreBean.id()) == 1){
                                isSumRoa[index] += showData.roa();
                                isSumAssetLiabilityRatio[index] += showData.asset_liability_ratio();
                            }else{
                                notSumRoa[index] += showData.roa();
                                notSumAssetLiabilityRatio[index] += showData.asset_liability_ratio();
                            }
                        }
                        if(flags.get(scoreBean.id()) == 1){
                            isSumScore += scoreBean.score();
                            isLength++;
                        }else{
                            notSumScore += scoreBean.score();
                            notLength++;
                        }
                    }
                    isMap.put("isScoreAvg",isSumScore/isLength);
                    isMap.put("roa",isSumRoa);
                    isMap.put("assetLiabilityRatio",isSumAssetLiabilityRatio);
                    isMap.put("isNum",isLength);
                    notMap.put("notScoreAvg",notSumScore/notLength);
                    notMap.put("roa",notSumRoa);
                    notMap.put("assetLiabilityRatio",notSumAssetLiabilityRatio);
                    notMap.put("notNum",notLength);
                    financialReportMap.put("is",isMap);
                    financialReportMap.put("not",notMap);
                }
            }).start();
            return null;
        }else{
            return financialReportMap;
        }
    }
}
