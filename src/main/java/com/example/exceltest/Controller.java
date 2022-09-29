package com.example.exceltest;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/baixar")
@RequiredArgsConstructor
public class Controller {


    private final Repository repository;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView exportRevisionsToExcel(ModelAndView modelAndView, HttpServletResponse response) {


        List<Entidade> revList = new ArrayList<>();
        revList.addAll(repository.findAll());
        revList.addAll(repository.findAll());


        System.out.println(revList.size());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss");
        String excelFileName = "Revisions_" + formatter.format(LocalDateTime.now()) + ".xlsx";
        SXSSFWorkbook wb = (new ExportRevisionResponseExcel()).exportExcel(new String[] { "REVISION ID",
                "CREATION DATE" }, revList);

        try {
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + excelFileName);
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            wb.dispose();
            outByteStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return modelAndView;

    }
}
