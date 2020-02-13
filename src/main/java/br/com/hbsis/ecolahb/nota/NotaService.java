package br.com.hbsis.ecolahb.nota;


import br.com.hbsis.ecolahb.boletim.BoletimService;
import br.com.hbsis.ecolahb.materia.MateriaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotaService.class);

    private final INotaRepository iNotaRepository;
    private final MateriaService materiaService;
    private final BoletimService boletimService;

    public NotaService(INotaRepository iNotaRepository, MateriaService materiaService, BoletimService boletimService) {
        this.iNotaRepository = iNotaRepository;
        this.materiaService = materiaService;
        this.boletimService = boletimService;
    }

    public NotaDTO save(NotaDTO notaDTO) {

        this.validate(notaDTO);

        LOGGER.info("Salvando nota");
        LOGGER.debug("Nota: {}", notaDTO);

        Nota nota = new Nota();
        nota.setNota(notaDTO.getNota());
        nota.setMateriaId(materiaService.findByMateriaId(notaDTO.getMateriaId()));
        nota.setBoletimId(boletimService.findByBoletimId(notaDTO.getBoletimId()));

        nota = this.iNotaRepository.save(nota);

        return NotaDTO.of(nota);
    }

    private void validate(NotaDTO notaDTO) {
        LOGGER.info("Validando nota");

        if (notaDTO == null) {
            throw new IllegalArgumentException("NotaDTO não deve ser nula");
        }

        if (StringUtils.isEmpty(notaDTO.getNota())) {
            throw new IllegalArgumentException("Nota não deve ser nula");
        }

        if (StringUtils.isEmpty(notaDTO.getMateriaId())) {
            throw new IllegalArgumentException("MateriaId não deve ser nulo");
        }

        if (StringUtils.isEmpty(notaDTO.getBoletimId())) {
            throw new IllegalArgumentException("BoletimId não deve ser nulo");
        }
    }


    public NotaDTO findById(Long id) {
        Optional<Nota> notaOptional = this.iNotaRepository.findById(id);

        if (notaOptional.isPresent()) {
            return NotaDTO.of(notaOptional.get());
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public List<Nota> findAll() {
       return this.iNotaRepository.findAll();

    }

    public NotaDTO update(NotaDTO notaDTO, Long id) {
        Optional<Nota> notaExistenteOptional = this.iNotaRepository.findById(id);

        if (notaExistenteOptional.isPresent()) {
            Nota notaExistente = notaExistenteOptional.get();

            LOGGER.info("Atualizando nota... id: [{}]", notaExistente.getId());
            LOGGER.debug("Payload: {}", notaDTO);
            LOGGER.debug("Nota Existente: {}", notaExistente);

            notaExistente.setNota(notaDTO.getNota());
            notaExistente.setMateriaId(materiaService.findByMateriaId(notaDTO.getMateriaId()));
            notaExistente.setBoletimId(boletimService.findByBoletimId(notaDTO.getBoletimId()));

            notaExistente = this.iNotaRepository.save(notaExistente);

            return NotaDTO.of(notaExistente);
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para nota de ID: [{}]", id);

        this.iNotaRepository.deleteById(id);
    }
}
