package br.com.hbsis.ecolahb.nota;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotaRest.class);

    private final NotaService notaService;

    @Autowired
    public NotaRest(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public NotaDTO save(@Valid @RequestBody NotaDTO notaDTO) {
        LOGGER.info("Recebendo solicitação de cadastro de nota...");
        LOGGER.debug("Payload: {}", notaDTO);

        return this.notaService.save(notaDTO);
    }

    @GetMapping("/{id}")
    public NotaDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.notaService.findById(id);
    }

//    @GetMapping("/")
//    public List<Nota> findAll() {
//
//        LOGGER.info("Recebendo find all");
//
//        return this.notaService.findAll();
//    }

    @GetMapping("/")
    public List<NotaDTO> findAll() {

        LOGGER.info("Recebendo find all");

        return this.notaService.findAll();
    }

    @GetMapping("/aluno/{id}")
    public List<NotaDTO> findByAluno(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.notaService.findAllByAlunoId_Id(id);
    }

    @PutMapping("/{id}")
    public NotaDTO udpate(@PathVariable("id") Long id, @RequestBody NotaDTO notaDTO) {
        LOGGER.info("Recebendo Update para aluno de ID: {}", id);
        LOGGER.debug("Payload: {}", notaDTO);

        return this.notaService.update(notaDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para nota de ID: {}", id);

        this.notaService.delete(id);
    }
}
