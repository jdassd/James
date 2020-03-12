package com.James.corporateportraitplatforms.utils;

import com.James.corporateportraitplatforms.mapper.CompanyExtendMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sweeneyhe.Ml;
import sweeneyhe.bean.*;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CharactersUtils {
    private static Ml ml;

    @Autowired
    public void setMl(Ml ml) {
        CharactersUtils.ml = ml;
    }

    public static void initData(String companyFilePath, String yearFilePath, String moneyFilePath, String knowledgeFilePath) {
        ml.initAndCleanData(companyFilePath, yearFilePath, moneyFilePath, knowledgeFilePath);
    }
    /**
     *
     * 判读僵尸企业
     *
     * @return map key 企业id， value 企业flag
     */
    public static Map<Integer, Integer> getFlags() {
        return ml.getFlags();
    }

    /**
     *
     * 获取标签
     *
     * @return map key 公司id value 标签id（List）
     */
    public static Map<Integer, List<Integer>> getTags() {
        return ml.getLabels();
    }

    public static List<BaseBean> getCompanyBeanList() {
        return ml.getBase();
    }

    public static List<KnowledgeBean> getKnowledgeBeanList() {
        return ml.getKnowledge();
    }

    public static List<MoneyBean> getMoneyBeanList() {
        return ml.getMoney();
    }

    public static List<YearBean> getYearBeanList() {
        return ml.getYear();
    }

    public static List<ScoreBean> getCompanyScoreList() {
        return ml.getScore();
    }

    public static Map<Integer, List<ShowData>> getCompanyShowData() {
        return ml.getShowData();
    }
}
