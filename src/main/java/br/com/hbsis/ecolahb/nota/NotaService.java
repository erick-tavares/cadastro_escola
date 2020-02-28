package br.com.hbsis.ecolahb.nota;


import br.com.hbsis.ecolahb.aluno.AlunoService;
import br.com.hbsis.ecolahb.boletim.BoletimService;
import br.com.hbsis.ecolahb.materia.MateriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotaService.class);

    private final INotaRepository iNotaRepository;
    private final MateriaService materiaService;
    private final AlunoService alunoService;
    private final BoletimService boletimService;

    public NotaService(INotaRepository iNotaRepository, MateriaService materiaService, AlunoService alunoService, BoletimService boletimService) {
        this.iNotaRepository = iNotaRepository;
        this.materiaService = materiaService;
        this.alunoService = alunoService;
        this.boletimService = boletimService;
    }

    public NotaDTO save(NotaDTO notaDTO) {

        this.validate(notaDTO);

        LOGGER.info("Salvando nota");
        LOGGER.debug("Nota: {}", notaDTO);

        Nota nota = new Nota();
        nota.setNota(notaDTO.getNota());
        nota.setMateriaId(materiaService.findByMateriaId(notaDTO.getMateriaId()));
        nota.setAlunoId(alunoService.findByAlunoId(notaDTO.getAlunoId()));
        nota.setBoletimId(boletimService.findByBoletimId(notaDTO.getBoletimId()));

        validarListaNota(notaDTO);
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

        if (notaDTO.getNota() > 10 || notaDTO.getNota() < 0) {
            throw new IllegalArgumentException("A nota deve ser de 0 a 10");
        }

        if (StringUtils.isEmpty(notaDTO.getMateriaId())) {
            throw new IllegalArgumentException("MateriaId não deve ser nulo");
        }

        if (StringUtils.isEmpty(notaDTO.getAlunoId())) {
            throw new IllegalArgumentException("AlunoId não deve ser nulo");
        }

        if (StringUtils.isEmpty(notaDTO.getBoletimId())) {
            throw new IllegalArgumentException("BoletimId não deve ser nulo");
        }
    }

    public void validarListaNota(NotaDTO notaDTO) {
        List<Nota> notaDoBoletim = this.findAllByBoletimId_IdAndMateriaId_Id(notaDTO.getBoletimId(), notaDTO.getMateriaId());
        if (notaDoBoletim.size() > 3) {
            throw new IllegalArgumentException("O aluno só pode ter no máximo 4 notas por matéria para cada boletim");
        }
    }

    public NotaDTO findById(Long id) {
        Optional<Nota> notaOptional = this.iNotaRepository.findById(id);

        if (notaOptional.isPresent()) {
            return NotaDTO.of(notaOptional.get());
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public List<NotaDTO> findAll() {
        List<Nota> notaList = iNotaRepository.findAll();
        List<NotaDTO> notaDTOList = new ArrayList<>();
        for (Nota nota : notaList) {
            notaDTOList.add(NotaDTO.of(nota));
        }
        return notaDTOList;
    }

    public List<NotaDTO> findAllByAlunoId_Id(Long alunoId) {
        List<Nota> notas = iNotaRepository.findAllByAlunoId_Id(alunoId);
        List<NotaDTO> notasDto = new ArrayList<>();
        for (Nota nota : notas) {
            notasDto.add(NotaDTO.of(nota));
        }
        return notasDto;
    }

    public List<Nota> findAllByAlunoId_IdAndPeriodoId_Id(Long alunoId, Long periodoId) {
        return this.iNotaRepository.findAllByBoletimId_AlunoId_IdAndBoletimId_PeriodoId_Id(alunoId, periodoId);
    }

    public List<Nota> findAllByBoletimId_IdAndMateriaId_Id(Long boletimId, Long materiaId) {
        return this.iNotaRepository.findAllByBoletimId_IdAndMateriaId_Id(boletimId, materiaId);
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
            notaExistente.setAlunoId(alunoService.findByAlunoId(notaDTO.getAlunoId()));
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
