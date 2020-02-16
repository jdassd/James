package com.James.corporateportraitplatforms.utils;

import com.James.corporateportraitplatforms.mapper.CompanyMapper;
import com.James.corporateportraitplatforms.mapper.KnowledgeReportMapper;
import com.James.corporateportraitplatforms.mapper.MoneyReportMapper;
import com.James.corporateportraitplatforms.mapper.YearReportMapper;
import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.model.KnowledgeReport;
import com.James.corporateportraitplatforms.model.MoneyReport;
import com.James.corporateportraitplatforms.model.YearReport;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private static KnowledgeReportMapper knowledgeReportMapper;
    private static CompanyMapper companyMapper;
    private static MoneyReportMapper moneyReportMapper;
    private static YearReportMapper yearReportMapper;

    @PostConstruct
    public void beforeInit(){
        knowledgeReportMapper = knowledgeReportMapper2;
        companyMapper = companyMapper2;
        moneyReportMapper = moneyReportMapper2;
        yearReportMapper = yearReportMapper2;
    }


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
     * 读取 downTemp 下的 csv 文件并存入mysql
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
                    for(int i = 0 ; i < dataList.size() ; i++){
                        if(i != 0){//不是第一行
                            String content = dataList.get(i);
                            //System.out.println("内容测试： " + content);
                            String[] contents = content.split(",");
                            try {
                                switch (size) {
                                    case 4:
                                        //knowledgeReport
                                        if (knowledgeReportMapper.insert(new KnowledgeReport(null, contents[0], contents[1], contents[2], contents[3])) != 1) {
                                            //deleteAll();
                                            return false;
                                        }
                                        break;
                                    case 9:
                                        if (companyMapper.insert(new Company(contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6], contents[7], contents[8], null, null, null, null)) != 1) {
                                            //deleteAll();
                                            return false;
                                        }
                                        break;
                                    case 10:
                                        if (moneyReportMapper.insert(new MoneyReport(null, contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6], contents[7], contents[8], contents[9])) != 1) {
                                            //deleteAll();
                                            return false;
                                        }
                                        break;
                                    case 11:
                                        if (yearReportMapper.insert(new YearReport(null, contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6], contents[7], contents[8], contents[9], contents[10])) != 1) {
                                            //deleteAll();
                                            return false;
                                        }
                                        break;
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                //deleteAll();
                                //System.out.println("插入数据库异常，清空数据库");
                            }
                            //System.out.println(size);
                        }else{
                            size = dataList.get(i).split(",").length;
                        }
                    }
                }
            }
            //读取完的文件直接删除，清空目录
            try {
                FileUtils.cleanDirectory(dir);
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
}
