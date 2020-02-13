package br.com.hbsis.ecolahb.boletim;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReportService {

    @Autowired

    private final BoletimService boletimService;


    public ReportService(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    public String exportReport (String reportFormat, Long boletim, Long materia) throws FileNotFoundException, JRException {
            String path = "C:\\Users\\erick.tavares\\Desktop\\Report";
            List<BoletimModel> boletimList = boletimService.preencherBoletim(boletim, materia);

            File file = ResourceUtils.getFile("classpath:boletim.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletimList);
            Map<String, Object> map = new HashMap<>();
            map.put("createdBy", "Escola HB");


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\boletim.html");
            }
            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\boletim.pdf");
            }
            return "Report gerado no caminho" + path;
        }


    }
