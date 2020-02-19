package br.com.hbsis.ecolahb.periodo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/periodos")
public class PeriodoRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoRest.class);

    private final PeriodoService periodoService;

    @Autowired
    public PeriodoRest(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @PostMapping
    public PeriodoDTO save(@Valid @RequestBody PeriodoDTO periodoDTO) {
        LOGGER.info("Recebendo solicitação de cadastro de periodo...");
        LOGGER.debug("Payload: {}", periodoDTO);

        return this.periodoService.save(periodoDTO);
    }

    @GetMapping("/{id}")
    public PeriodoDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.periodoService.findById(id);
    }

    @PutMapping("/{id}")
    public PeriodoDTO udpate(@PathVariable("id") Long id, @RequestBody PeriodoDTO periodoDTO) {
        LOGGER.info("Recebendo Update para periodo de ID: {}", id);
        LOGGER.debug("Payload: {}", periodoDTO);

        return this.periodoService.update(periodoDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para periodo de ID: {}", id);

        this.periodoService.delete(id);
    }
}
