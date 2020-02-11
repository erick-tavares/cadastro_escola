package br.com.hbsis.ecolahb.boletim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/boletins")
public class BoletimRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimRest.class);

    private final BoletimService boletimService;

    @Autowired
    public BoletimRest(BoletimService boletimService) {
        this.boletimService = boletimService;
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
