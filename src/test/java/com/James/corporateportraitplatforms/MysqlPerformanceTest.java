package com.James.corporateportraitplatforms;


import com.James.corporateportraitplatforms.mapper.CompanyMapper;
import com.James.corporateportraitplatforms.mapper.DataMapper;
import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.service.CsvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sweeneyhe.bean.BaseBean;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MysqlPerformanceTest {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private CsvService csvService;

    @Test
    public void companyBatchInsertTest() {
        List<Company> companyList = new ArrayList<>(30000);
        int dataSize = 15000;

        /* *
         * ====================================================================
         *                           批量插入不分批
         * */
        batchCut(companyList, dataSize, dataSize);
        /* *
        * ====================================================================
        *                           批量插入分批 10
        * */
//        batchCut(companyList, dataSize, 10);

        /* *
         * ====================================================================
         *                           批量插入分批 3000
         * */

        batchCut(companyList, dataSize, 3000);

        /* *
         * ====================================================================
         *                           批量插入分批 4000
         * */

        batchCut(companyList, dataSize, 4000);

        /* *
         * ====================================================================
         *                           批量插入分批 5000
         * */

        batchCut(companyList, dataSize, 5000);
    }

    public void companyListCreate(List<Company> companyList, int size) {
        companyList.clear();
        for (int i = 0; i < size; i++) {
            Company company = Company.builder().id((10000 + i) + "")
                    .registerTime("2020")
                    .registerMoney("20000")
                    .industry("交通运输业")
                    .city("湖南")
                    .companyType("农民专业合作社")
                    .controllerType("企业法人")
                    .controllerProportion("0.99")
                    .flag("1").build();
            companyList.add(company);
        }
    }

    public void batchCut(List<Company> companyList, int dataSize, int backSize) {
        companyListCreate(companyList, dataSize);
        dataMapper.cleanData();

        long startTime = new Date().getTime();
        for (int i = 0; i < dataSize;) {
            int endIndex = Math.min(i + backSize, dataSize);
            final List<Company> subCompanyList = companyList.subList(i, endIndex);
//            companyMapper.insertBatch(subCompanyList);
            i = endIndex;
        }
        long endTime = new Date().getTime();

        System.out.println("分批（" + backSize + "）插入耗时：" + (endTime- startTime));
    }

    @Test
    public void fixTest() {
        BaseBean baseBean = new BaseBean(8888888, 20030, 29, "dfsrf", "fhdsjf", "ffsf", "fsefs", 22);
        final HashMap<Integer, Integer> map = new HashMap<>();
        map.put(8888888, 1);
        companyMapper.insertBatch_(Collections.singletonList(baseBean), map);
    }

    @Test
    public void csvTest() {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(1, 1);
//        map.put(2, 1);
//        csvService.saveCompanyFlag2File(map);

//        csvService.downCsvFile();
    }
}
