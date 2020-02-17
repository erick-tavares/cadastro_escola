package br.com.hbsis.ecolahb.boletim;


import br.com.hbsis.ecolahb.materia.Materia;
import br.com.hbsis.ecolahb.materia.MateriaService;
import br.com.hbsis.ecolahb.nota.Nota;
import br.com.hbsis.ecolahb.nota.NotaService;
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
    private final MateriaService materiaService;
    private final NotaService notaService;

    public ReportService(BoletimService boletimService, MateriaService materiaService, NotaService notaService) {
        this.boletimService = boletimService;
        this.materiaService = materiaService;
        this.notaService = notaService;
    }

    public List<BoletimModel> preencherBoletim(Long boletimId) {
        List<BoletimModel> boletimModelList = new ArrayList<>();
        Boletim boletimExistente = boletimService.findByBoletimId(boletimId);

        List<Materia> materiaList = materiaService.findAll();
        for (Materia materia : materiaList) {
            List<Nota> notaList = notaService.findAllByAlunoId_IdAndPeriodoId_Id(boletimExistente.getAlunoId().getId(), boletimExistente.getPeriodoId().getId());
            try {
                BoletimModel boletimModel = new BoletimModel();

                double[] notas = new double[4];
                int contador = 0;
                for (int i = 0; i < notaList.size(); i++) {
                    if (notaList.get(i).getMateriaId().getId().equals(materia.getId())) {
                        notas[contador] = notaList.get(i).getNota();
                        contador++;
                    }
                }
                double nota1 = notas [0];
                double nota2 = notas [1];
                double nota3 = notas [2];
                double nota4 = notas [3];

                double media = ((nota1 + nota2 + nota3 + nota4) / notas.length);

                boletimModel.setAluno(boletimService.findByBoletimId(boletimId).getAlunoId().getNome());
                boletimModel.setMateria(materiaService.findByMateriaId(materia.getId()).getNome());

                boletimModel.setPeriodo(boletimService.findByBoletimId(boletimId).getPeriodoId().getDescricao());
                boletimModel.setNota1(String.valueOf(nota1));
                boletimModel.setNota2(String.valueOf(nota2));
                boletimModel.setNota3(String.valueOf(nota3));
                boletimModel.setNota4(String.valueOf(nota4));

                boletimModel.setMedia(String.valueOf(media));
                boletimModelList.add(boletimModel);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return boletimModelList;
    }




    public String exportReport (Long boletim, String reportFormat ) throws FileNotFoundException, JRException {
            String path = "C:\\Users\\erick.tavares\\Desktop\\Report";
            List<BoletimModel> boletimList = this.preencherBoletim(boletim);

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
