package br.com.hbsis.ecolahb.boletim;

import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/boletins")
public class BoletimRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimRest.class);

    private final BoletimService boletimService;

    private final ReportService reportService;

    @Autowired
    public BoletimRest(BoletimService boletimService, ReportService reportService) {
        this.boletimService = boletimService;
        this.reportService = reportService;
    }

    @PostMapping
    public BoletimDTO save(@Valid @RequestBody BoletimDTO boletimDTO) {
        LOGGER.info("Recebendo solicitação de cadastro de boletim...");
        LOGGER.debug("Payload: {}", boletimDTO);

        return this.boletimService.save(boletimDTO);
    }

    @GetMapping("/{id}")
    public BoletimDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.boletimService.findById(id);
    }

    @GetMapping("/")
    public List<BoletimDTO> findAll() {

        LOGGER.info("Recebendo find all");

        return this.boletimService.findAll();
    }

//    @GetMapping("/report/{boletim}/{format}")
//    public String generateReport(@PathVariable ("boletim") Long boletim, @PathVariable("format") String format) throws FileNotFoundException, JRException {
//        return reportService.exportReport(boletim, format);
//    }

    @GetMapping("/report/{boletim}")
    public ResponseEntity<byte[]> generateReport(@PathVariable("boletim") Long boletim) throws FileNotFoundException, JRException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(reportService.exportReport(boletim), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<BoletimDTO> findByAluno(@PathVariable("alunoId") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.boletimService.boletimDoAluno(id);
    }


    @PutMapping("/{id}")
    public BoletimDTO udpate(@PathVariable("id") Long id, @RequestBody BoletimDTO boletimDTO) {
        LOGGER.info("Recebendo Update para boletim de ID: {}", id);
        LOGGER.debug("Payload: {}", boletimDTO);

        return this.boletimService.update(boletimDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para boletim de ID: {}", id);

        this.boletimService.delete(id);
    }
}
