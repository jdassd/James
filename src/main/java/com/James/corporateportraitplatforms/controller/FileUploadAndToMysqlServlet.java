package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import com.James.corporateportraitplatforms.service.CsvToMysqlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/upload")
public class FileUploadAndToMysqlServlet {
    @Autowired
    private CsvToMysqlService csvToMysqlService;

    /**
     * 点击分析企业数据按钮
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/csv.do",method = {RequestMethod.GET})
    public AjaxResponseModel<String> CsvUpload(HttpServletResponse response) throws IOException {
        //返回状态码
        int code = csvToMysqlService.csvToMysql();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String,Integer> map = new HashMap<>();
//        map.put("code",code);
//        String json = objectMapper.writeValueAsString(map);
//        response.getWriter().write(json);

        log.info("CsvUpload done");

        return AjaxResponseModel.<String>builder().code(code).build();
    }
}
