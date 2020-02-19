package br.com.hbsis.ecolahb.materia;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/materias")
public class MateriaRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MateriaRest.class);

    private final MateriaService materiaService;

    @Autowired
    public MateriaRest(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping
    public MateriaDTO save(@Valid @RequestBody MateriaDTO materiaDTO) {
        LOGGER.info("Recebendo solicitação de cadastro de materia...");
        LOGGER.debug("Payload: {}", materiaDTO);

        return this.materiaService.save(materiaDTO);
    }

    @GetMapping("/{id}")
    public MateriaDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.materiaService.findById(id);
    }

    @GetMapping("/")
    public List<MateriaDTO> findAll() {

        LOGGER.info("Recebendo find all");

        return this.materiaService.findAllDTO();
    }

    @PutMapping("/{id}")
    public MateriaDTO udpate(@PathVariable("id") Long id, @RequestBody MateriaDTO materiaDTO) {
        LOGGER.info("Recebendo Update para materia de ID: {}", id);
        LOGGER.debug("Payload: {}", materiaDTO);

        return this.materiaService.update(materiaDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para materia de ID: {}", id);

        this.materiaService.delete(id);
    }
}
