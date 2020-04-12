package com.James.corporateportraitplatforms.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.catalyst.catalog.DatabaseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 阿里云 OSS 工具类
 */
@Slf4j
public class AliOssUtils {
    // Endpoint 北京
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "LTAI4FtCrAPLAc93H5tRvdDw";
    private static String accessKeySecret = "pyTo12HjqJje4pbG1UjDCn8Y5MAclc";
    private static String bucketName = "gweike";

    /**
     * 下载文件
     * @throws IOException
     */
    public static boolean down() throws IOException {
        long startTime = new Date().getTime();
        boolean flag = false;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        //指定下载文件存放位置
        File dir = new File("downTemp");
        if (dir.exists()){
            if(dir.isDirectory()){
            }else{
                if(dir.delete()) {
                    dir.mkdir();
                }
            }
        }else{
            dir.mkdir();
        }
        //System.out.println(objectListing.getObjectSummaries().size());
        if(objectListing.getObjectSummaries().size() == 4) {
            flag = true;
            // objectListing.getObjectSummaries获取所有文件的描述信息。
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                //System.out.println(" - " + objectSummary.getKey() + "  " +
                //        "(size = " + objectSummary.getSize() + ")");
                // <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
                String objectName = objectSummary.getKey();
                // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
                OSSObject ossObject = ossClient.getObject(bucketName, objectName);
                // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
                InputStream content = ossObject.getObjectContent();
                if (content != null) {
                    OutputStream out = new FileOutputStream(dir.getAbsolutePath() + File.separatorChar + objectName);
                    //System.out.println("文件路径： " + dir.getAbsolutePath() + File.separatorChar + objectName);
                    int size = 0;
                    int len = 0;
                    byte[] buf = new byte[20480];
                    while ((size = content.read(buf)) != -1) {
                        len += size;
                        out.write(buf, 0, size);
                        // 控制台打印文件下载的百分比情况
                        //System.out.println("下载了-------> " + len * 100 / objectSummary.getSize() + "%");
                    }
                    out.close();
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }
        }
        //关闭OSSClient。
        ossClient.shutdown();
        log.info("down dataFile and change encoding used Time {}", (double)(new Date().getTime() - startTime) / 1000);
        return flag;
    }

    /**
     * 删除文件
     * @return
     */
    public static boolean deleteFiles(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        // objectListing.getObjectSummaries获取所有文件的描述信息。
        List<String> keys = new ArrayList<String>();
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            keys.add(objectSummary.getKey());
        }
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        //打印删除结果
        //System.out.println(deletedObjects);
        //关闭OSSClient。
        ossClient.shutdown();
        return deletedObjects != null;
    }

    public static boolean down_() throws IOException {
        long startTime = new Date().getTime();
        boolean flag = false;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        //指定下载文件存放位置
        File dir = new File("downTemp");
        if (dir.exists()){
            if(dir.isDirectory()){
            }else{
                if(dir.delete()) {
                    dir.mkdir();
                }
            }
        }else{
            dir.mkdir();
        }
        //System.out.println(objectListing.getObjectSummaries().size());
        if(objectListing.getObjectSummaries().size() == 4) {
            flag = true;
            // objectListing.getObjectSummaries获取所有文件的描述信息。
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                //System.out.println(" - " + objectSummary.getKey() + "  " +
                //        "(size = " + objectSummary.getSize() + ")");
                // <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
                String objectName = objectSummary.getKey();
                // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
                OSSObject ossObject = ossClient.getObject(bucketName, objectName);
                // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
                InputStream content = ossObject.getObjectContent();
                if (content != null) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(content, "GB2312"));
                    FileWriter out = new FileWriter(dir.getAbsolutePath() + File.separatorChar + objectName);
                    //System.out.println("文件路径： " + dir.getAbsolutePath() + File.separatorChar + objectName);
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        out.write(line + System.lineSeparator());
                        // 控制台打印文件下载的百分比情况
                        //System.out.println("下载了-------> " + len * 100 / objectSummary.getSize() + "%");
                    }
                    out.close();
                    br.close();
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }
        }
        //关闭OSSClient。
        ossClient.shutdown();
        log.info("down dataFile and change encoding used Time {}", (double)(new Date().getTime() - startTime) / 1000);
        return flag;
    }
}
