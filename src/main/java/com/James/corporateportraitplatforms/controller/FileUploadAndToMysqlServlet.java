package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.service.CsvToMysqlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class FileUploadAndToMysqlServlet {
    @Autowired
    private CsvToMysqlService csvToMysqlService;

    @RequestMapping(value = "/csv.do",method = {RequestMethod.GET})
    public void CsvUpload(HttpServletResponse response) throws IOException {
        //返回状态码
        int code = csvToMysqlService.csvToMysql();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        map.put("code",code);
        String json = objectMapper.writeValueAsString(map);
        response.getWriter().write(json);
    }
}
