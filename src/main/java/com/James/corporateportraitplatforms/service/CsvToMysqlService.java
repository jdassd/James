package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.model.KnowledgeReport;
import com.James.corporateportraitplatforms.model.MoneyReport;
import com.James.corporateportraitplatforms.model.YearReport;
import com.James.corporateportraitplatforms.utils.AliOssUtils;
import com.James.corporateportraitplatforms.utils.CharactersUtils;
import com.James.corporateportraitplatforms.utils.CsvUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class CsvToMysqlService {
    /**
     * 将上传的CSV文件分析后存入mysql
     *
     * @return 101 : 下载失败
     * 102 : 未上传文件数据或上传文件数据不足 (4个)
     * 201 : 导入数据库失败
     * 301 : 清空 OSS 数据失败
     * 302 : 数据文件不足且无法删除数据文件
     * 1   : 操作成功
     */
    public int csvToMysql() {
        //下载 OSS 中的数据文件
        try {
            if (!AliOssUtils.down_()) {
                //在阿里云 OSS 同步删除
                if (!AliOssUtils.deleteFiles()) {
                    return 302;
                }
                return 102;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 101;
        }


        // 2020-3-10 测试代码开始
        CsvUtils.test();


        //将下载的文件分析后导入数据库
//        if(!CsvUtils.analysis()){
//            return 201;
//        }
        //在阿里云 OSS 同步删除
        //if(!AliOssUtils.deleteFiles()){
        //    return 301;
        //}
        return 1;
    }
}
