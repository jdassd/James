package com.James.corporateportraitplatforms.utils;

import com.James.corporateportraitplatforms.mapper.*;
import com.James.corporateportraitplatforms.model.*;
import com.James.corporateportraitplatforms.service.CsvService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sweeneyhe.bean.YearBean;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CSV工具类
 */
@Component
public class CsvUtils {
    @Autowired
    private KnowledgeReportMapper knowledgeReportMapper2;
    @Autowired
    private CompanyMapper companyMapper2;
    @Autowired
    private MoneyReportMapper moneyReportMapper2;
    @Autowired
    private YearReportMapper yearReportMapper2;

    public static Map<Integer,Integer> flagsMap = null;
    public static List<YearBean> yearBeanList = null;

    private static KnowledgeReportMapper knowledgeReportMapper;
    private static CompanyMapper companyMapper;
    private static MoneyReportMapper moneyReportMapper;
    private static YearReportMapper yearReportMapper;
    private static TagCompanyMapper tagCompanyMapper;

    @PostConstruct
    public void beforeInit(){
        knowledgeReportMapper = knowledgeReportMapper2;
        companyMapper = companyMapper2;
        moneyReportMapper = moneyReportMapper2;
        yearReportMapper = yearReportMapper2;
    }

    @Autowired
    public void setTagCompanyMapper(TagCompanyMapper tagCompanyMapper) {
        CsvUtils.tagCompanyMapper = tagCompanyMapper;
    }

    //    2020-3-10 Devil 测试添加开始
    private static CompanyShowDataMapper companyShowDataMapper;
    private static CompanyScoreMapper companyScoreMapper;

    private static CsvService csvService;

    @Autowired
    public void setCompanyScoreMapper(CompanyScoreMapper scoreMapper) {
        CsvUtils.companyScoreMapper = scoreMapper;
    }
    @Autowired
    public void setCompanyShowDataMapper(CompanyShowDataMapper companyShowDataMapper) {
        CsvUtils.companyShowDataMapper = companyShowDataMapper;
    }
    @Autowired
    public void setCsvService(CsvService service) {
        CsvUtils.csvService = service;
    }
    //    2020-3-16 Devil 测试添加结束


    /**
     *      * 写入 
     *      * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     *      * @param dataList 数据
     *      * @return
     *      
     */
    public static boolean exportCsv(File file, List<String> dataList) {
        boolean isSucess = false;
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    bw.append(data).append("\r");
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSucess;
    }

    /**
     *      * 读取
     *      * @param file csv文件(路径+文件)
     *      * @return
     *      
     */
    public static List<String> importCsv(File file) {
        List<String> dataList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GB2312"));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }

    /**
     * 插入 Tags 表
     * @return
     */
    public static boolean importCsvTags(String companyFilePath){
//        tagCompanyMapper.insertBatch(CharactersUtils.getTags(companyFilePath));
        //读取完的文件直接删除，清空目录
        try {
            FileUtils.cleanDirectory(new File("downTemp"));
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("清空目录失败");
            //try {
            //    Thread.sleep(3000);
            //} catch (InterruptedException ex) {
            //    ex.printStackTrace();
            //}
        }
        //System.out.println("读取完毕");
        return true;
    }

    /**
     * 读取 downTemp 下的 company_csv 文件和分析的数据存入mysql
     * @return
     */
    public static boolean importCsvCompany(){
        String companyFilePath = null;
        File dir = new File("downTemp");
        if(dir.exists()) {
            String[] suffixes = {"csv"};
            Collection<File> listFiles = FileUtils.listFiles(dir, suffixes, true);
            for (File file : listFiles) {
                List<String> dataList = importCsv(file);
                int size = 0;
                if (!dataList.isEmpty()) {
                    size = dataList.get(0).split(",").length;
                    if (size == 9) {
                        List list = new ArrayList<Company>();
                        companyFilePath = file.getAbsolutePath();
                        for (int i = 1; i < dataList.size(); i++) {
                            String content = dataList.get(i);
                            //System.out.println("内容测试： " + content);
                            String[] contents = content.split(",");
                            list.add(new Company(contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6], contents[7], flagsMap.get(Integer.parseInt(contents[0])) != null ? flagsMap.get(Integer.parseInt(contents[0])).toString() : "-1", null, null, null, null));
                        }
                        companyMapper.insertBatch(list);
                    }
                }
            }
            // company 表插入完成后插入 tags 表
            final String finalCompanyFilePath = companyFilePath;
            new Thread(() -> importCsvTags(finalCompanyFilePath)).start();

        }
        return true;
    }

    /**
     * 读取 downTemp 下的 csv 文件和分析的数据存入mysql（除 company）
     * @return
     */
    public static boolean importCsv(){
        File dir = new File("downTemp");
        if(dir.exists()) {
            String[] suffixes = {"csv"};
            Collection<File> listFiles = FileUtils.listFiles(dir, suffixes, true);
            for(File file : listFiles) {
                List<String> dataList = importCsv(file);
                int size = 0;
                if(!dataList.isEmpty()){
                    size = dataList.get(0).split(",").length;
                    List list = null;
                    switch (size){
                        case 4:
                            list = new ArrayList<KnowledgeReport>();
                            break;
                        case 10:
                            list = new ArrayList<MoneyReport>();
                            break;
                        case 11:
                            list = new ArrayList<YearReport>();
                            break;
                    }
                    for (int i = 1; i < dataList.size(); i++) {
                        String content = dataList.get(i);
                        //System.out.println("内容测试： " + content);
                        String[] contents = content.split(",");
                        switch (size) {
                            case 4:
                                list.add(new KnowledgeReport(null, contents[0], contents[1], contents[2], contents[3]));
                                break;
                            case 10:
                                list.add(new MoneyReport(null, contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6], contents[7], contents[8], contents[9]));
                                break;
                            case 11:
                                list.add(new YearReport(null, contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6], contents[7], contents[8], contents[9], contents[10]));
                                break;
                        }
                    }
                    switch (size){
                        case 4:
                            knowledgeReportMapper.insertBatch(list);
                            break;
                        case 10:
                            moneyReportMapper.insertBatch(list);
                            break;
                        case 11:
                            yearReportMapper.insertBatch(list);
                            break;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 清空数据库
     */
    public static void deleteAll(){
        knowledgeReportMapper.deleteAll();
        companyMapper.deleteAll();
        moneyReportMapper.deleteAll();
        yearReportMapper.deleteAll();
    }

    /**
     * 分析数据并调用 importCsv 方法
     */
    public static boolean analysis(){
        new Thread(CsvUtils::importCsv).start();
        String fileStr1 = null;
        String fileStr2 = null;
        File dir = new File("downTemp");
        if(dir.exists()) {
            String[] suffixes = {"csv"};
            Collection<File> listFiles = FileUtils.listFiles(dir, suffixes, true);
            for (File file : listFiles) {
                List<String> dataList = importCsv(file);
                int size = 0;
                if (!dataList.isEmpty()) {
                    size = dataList.get(0).split(",").length;
                    switch (size) {
                        case 10:
                            fileStr2 = file.getAbsolutePath();
                            break;
                        case 11:
                            fileStr1 = file.getAbsolutePath();
                            break;
                    }
                }
            }
//            flagsMap = CharactersUtils.getFlags(fileStr1,fileStr2);

            return importCsvCompany();
        }
        return false;
    }

    // 2020-3-10 devil 测试添加
    public static void test() {
        String companyFilePath = null;
        String moneyFilePath = null;
        String yearFilePath = null;
        String knowledgeFilePath = null;
        File dir = new File("downTemp");
        if (dir.exists()) {
            String[] suffixes = {"csv"};
            Collection<File> listFiles = FileUtils.listFiles(dir, suffixes, true);
            for (File file : listFiles) {
                int size = 0;
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                    final String lineString = br.readLine();
                    size = lineString.split(",").length;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (size) {
                    case 4:
                        knowledgeFilePath = file.getAbsolutePath();
                        break;
                    case 10:
                        moneyFilePath = file.getAbsolutePath();
                        break;
                    case 11:
                        yearFilePath = file.getAbsolutePath();
                        break;
                    case 9:
                        companyFilePath = file.getAbsolutePath();
                        break;
                }
            }
        }

        CharactersUtils.initData(companyFilePath, yearFilePath, moneyFilePath, knowledgeFilePath);
        final Map<Integer, Integer> flags = CharactersUtils.getFlags();
        csvService.saveCompanyFlag2File(flags);
        flagsMap = flags;
        companyMapper.insertBatch_(CharactersUtils.getCompanyBeanList(), flags);
        final Map<Integer, List<Integer>> tags = CharactersUtils.getTags();
        tagCompanyMapper.insertBatch(tags);
        knowledgeReportMapper.insertBatch_(CharactersUtils.getKnowledgeBeanList());
        yearBeanList = CharactersUtils.getYearBeanList();
        yearReportMapper.insertBatch_(yearBeanList);
        moneyReportMapper.insertBatch_(CharactersUtils.getMoneyBeanList());

        companyShowDataMapper.insertBatch(CharactersUtils.getCompanyShowData());
        companyScoreMapper.insertBatch(CharactersUtils.getCompanyScoreList());
    }
}
