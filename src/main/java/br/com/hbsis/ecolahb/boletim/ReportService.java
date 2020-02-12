package br.com.hbsis.ecolahb.boletim;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private final IBoletimRepository iBoletimRepository;

    public ReportService(IBoletimRepository iBoletimRepository) {
        this.iBoletimRepository = iBoletimRepository;
    }


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\erick.tavares\\Desktop\\Report";
        List<Boletim> boletimList = iBoletimRepository.findAll();
        File file = ResourceUtils.getFile("classpath:boletim.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletimList);
        Map<String, Object> map = new HashMap<>();
        map.put("createdBy", "Boletim");

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
