package com.James.corporateportraitplatforms.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class CsvService {

    @Autowired
    private ServletContext servletContext;

    private final String csvDir = File.separatorChar + "csv";


    /**
     * @param flags key:companyId value:flag 的map
     * @return 0：操作成功 1：操作失败
     */
    public int saveCompanyFlag2File(Map<Integer, Integer> flags) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        File path = getDir();

        if (!path.exists())
            path.mkdirs();

        String csvFilePath = path.getPath() + File.separatorChar + "JSU-James-company-sort-result-" + sdf.format(new Date()) + ".csv";

        try (FileWriter fw = new FileWriter(csvFilePath)) {
            for (Map.Entry<Integer, Integer> enter : flags.entrySet()) {
                Integer id = enter.getKey();
                Integer flag = enter.getValue();
                fw.write(id + "," + flag + System.lineSeparator());
            }
        } catch (IOException e) {
            log.error(e.toString());
            return 1;
        }

        log.info("flags file save to {}", csvFilePath);
        return 0;
    }

    public void downCsvFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File csvDir = getDir();
        final File[] files = csvDir.listFiles((dir, name) -> name.endsWith(".csv"));

        if (files == null || files.length == 0) return;

        Arrays.sort(files, (File f1, File f2) -> (int) (f2.lastModified() - f1.lastModified()));
        File csvFile = files[0];
        final String csvFilePath = csvFile.getAbsolutePath();

        final ServletContext servletContext = request.getServletContext();
        final String mimeType = servletContext.getMimeType(csvFilePath);
        String disposition = String.format("attachment; filename=\"%s\"", csvFile.getName());
        response.setContentType(mimeType);
        response.setContentLengthLong(csvFile.length());
        response.setHeader("Content-Disposition", disposition);

        try(FileInputStream in = new FileInputStream(csvFilePath)) {
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        }

    }

    private File getDir() {
        return new File(servletContext.getRealPath(csvDir));
    }
}
