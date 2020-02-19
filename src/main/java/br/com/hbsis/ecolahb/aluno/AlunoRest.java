package br.com.hbsis.ecolahb.aluno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/alunos")
public class AlunoRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoRest.class);

    private final AlunoService alunoService;

    @Autowired
    public AlunoRest(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoDTO save(@Valid @RequestBody AlunoDTO alunoDTO) {
        LOGGER.info("Recebendo solicitação de cadastro de aluno...");
        LOGGER.debug("Payload: {}", alunoDTO);

        return this.alunoService.save(alunoDTO);
    }


    @GetMapping("/{id}")
    public AlunoDTO find(@PathVariable("id") Long id) {

        LOGGER.info("Recebendo find by ID... id: [{}]", id);

        return this.alunoService.findById(id);
    }

    @GetMapping("/")
    public List<AlunoDTO> findAll() {

        LOGGER.info("Recebendo find all");

        return this.alunoService.findAll();
    }

    @PutMapping("/{id}")
    public AlunoDTO udpate(@PathVariable("id") Long id, @RequestBody AlunoDTO alunoDTO) {
        LOGGER.info("Recebendo Update para aluno de ID: {}", id);
        LOGGER.debug("Payload: {}", alunoDTO);

        return this.alunoService.update(alunoDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Recebendo Delete para aluno de ID: {}", id);

        this.alunoService.delete(id);
    }
}
