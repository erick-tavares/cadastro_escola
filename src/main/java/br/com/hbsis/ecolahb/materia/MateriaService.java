package br.com.hbsis.ecolahb.materia;


import br.com.hbsis.ecolahb.aluno.Aluno;
import br.com.hbsis.ecolahb.aluno.AlunoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MateriaService.class);

    private final IMateriaRepository iMateriaRepository;

    public MateriaService(IMateriaRepository iMateriaRepository) {
        this.iMateriaRepository = iMateriaRepository;
    }

    public MateriaDTO save(MateriaDTO materiaDTO) {

        this.validate(materiaDTO);

        LOGGER.info("Salvando materia");
        LOGGER.debug("Materia: {}", materiaDTO);

        Materia materia = new Materia();
        materia.setNome(materiaDTO.getNome());

        materia = this.iMateriaRepository.save(materia);

        return MateriaDTO.of(materia);
    }

    private void validate(MateriaDTO materiaDTO) {
        LOGGER.info("Validando Materia");

        if (materiaDTO == null) {
            throw new IllegalArgumentException("MateriaDTO não deve ser nula");
        }

        if (StringUtils.isEmpty(materiaDTO.getNome())) {
            throw new IllegalArgumentException("Nome não deve ser nulo");
        }

    }

    public List<Materia> findAll () {
        return this.iMateriaRepository.findAll();
    }

    public MateriaDTO findById(Long id) {
        Optional<Materia> materiaOptional = this.iMateriaRepository.findById(id);

        if (materiaOptional.isPresent()) {
            return MateriaDTO.of(materiaOptional.get());
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public Materia findByMateriaId (Long id){
        Optional<Materia> materiaOptional = this.iMateriaRepository.findById(id);
        if (materiaOptional.isPresent()) {
            return materiaOptional.get();
        }
        throw new IllegalArgumentException(String.format("Materia %s não está cadastrada", id));
    }


    public MateriaDTO update(MateriaDTO materiaDTO, Long id) {
        Optional<Materia> materiaExistenteOptional = this.iMateriaRepository.findById(id);

        if (materiaExistenteOptional.isPresent()) {
            Materia materiaExistente = materiaExistenteOptional.get();

            LOGGER.info("Atualizando materia... id: [{}]", materiaExistente.getId());
            LOGGER.debug("Payload: {}", materiaDTO);
            LOGGER.debug("Materia Existente: {}", materiaExistente);

            materiaExistente.setNome(materiaDTO.getNome());

            materiaExistente = this.iMateriaRepository.save(materiaExistente);

            return MateriaDTO.of(materiaExistente);
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para materia de ID: [{}]", id);

        this.iMateriaRepository.deleteById(id);
    }
}
