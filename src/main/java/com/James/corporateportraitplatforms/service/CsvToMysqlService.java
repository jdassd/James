package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.utils.AliOssUtils;
import com.James.corporateportraitplatforms.utils.CsvUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@Service
public class CsvToMysqlService {
    /**
     * 将上传的CSV文件分析后存入mysql
     * @return
     * 101 : 下载失败
     * 102 : 未上传文件数据或上传文件数据不足 (4个)
     * 201 : 导入数据库失败
     * 301 : 清空 OSS 数据失败
     * 302 : 数据文件不足且无法删除数据文件
     * 1   : 操作成功
     */
    public int csvToMysql(){
        //下载 OSS 中的数据文件
        try {
            if(!AliOssUtils.down()){
                //在阿里云 OSS 同步删除
                if(!AliOssUtils.deleteFiles()){
                    return 302;
                }
                return 102;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 101;
        }
        //将下载的文件分析后导入数据库
        if(!CsvUtils.analysis()){
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
            return 201;
        }
        //在阿里云 OSS 同步删除
        //if(!AliOssUtils.deleteFiles()){
        //    return 301;
        //}
        return 1;
    }
}
