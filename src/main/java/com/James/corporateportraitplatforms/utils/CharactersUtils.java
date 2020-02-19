package com.James.corporateportraitplatforms.utils;

import com.James.corporateportraitplatforms.mapper.CompanyExtendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sweeneyhe.Ml;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class CharactersUtils {
    private static Ml ml;

    @Autowired
    public void setMl(Ml ml) {
        CharactersUtils.ml = ml;
    }


    /**
     *
     * 判读僵尸企业
     *
     * @param yearReportFilePath 年报表文件路径
     * @param moneyReportFilePath 资产报表文件路径
     * @return map key 企业id， value 企业flag
     */
    public static Map<Integer, Integer> getFlags(String yearReportFilePath, String moneyReportFilePath) {
        return ml.getFlags(yearReportFilePath, moneyReportFilePath);
    }


    /**
     *
     * 获取标签
     *
     * @param companyFilePath 公司文件路径
     * @return map key 公司id value 标签id（List）
     */
    public static Map<Integer, List<Integer>> getTags(String companyFilePath) {
        return ml.getLabels("", "", companyFilePath, "");
    }
}
