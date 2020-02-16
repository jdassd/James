package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.utils.AliOssUtils;
import com.James.corporateportraitplatforms.utils.CsvUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CsvToMysqlService {
    /**
     * 将上传的CSV文件存入mysql
     * @return
     * 101 : 下载失败
     * 102 : 未上传文件数据或上传文件数据不足 (4个)
     * 201 : 导入数据库失败
     * 301 : 清空 OSS 数据失败
     * 1   : 操作成功
     */
    public int csvToMysql(){
        //下载 OSS 中的数据文件
        try {
            if(!AliOssUtils.down()){
                return 102;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 101;
        }
        //将下载的文件数据导入数据库
        if(!CsvUtils.importCsv()){
            return 201;
        }
        //在阿里云 OSS 同步删除
        //if(!AliOssUtils.deleteFiles()){
        //    return 301;
        //}
        return 1;
    }
}
