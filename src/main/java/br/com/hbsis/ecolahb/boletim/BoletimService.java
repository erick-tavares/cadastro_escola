package br.com.hbsis.ecolahb.boletim;


import br.com.hbsis.ecolahb.aluno.AlunoService;
import br.com.hbsis.ecolahb.materia.Materia;
import br.com.hbsis.ecolahb.periodo.PeriodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class BoletimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimService.class);

    private final IBoletimRepository iBoletimRepository;
    private final AlunoService alunoService;
    private final PeriodoService periodoService;

    public BoletimService(IBoletimRepository iBoletimRepository, AlunoService alunoService, PeriodoService periodoService) {
        this.iBoletimRepository = iBoletimRepository;
        this.alunoService = alunoService;
        this.periodoService = periodoService;
    }

    public BoletimDTO save(BoletimDTO boletimDTO) {

        this.validate(boletimDTO);

        LOGGER.info("Salvando boletim");
        LOGGER.debug("Boletim: {}", boletimDTO);

        Boletim boletim = new Boletim();
        boletim.setAlunoId(alunoService.findByAlunoId(boletimDTO.getAlunoId()));
        boletim.setPeriodoId(periodoService.findByPeriodoId(boletimDTO.getPeriodoId()));

        boletim = this.iBoletimRepository.save(boletim);

        return BoletimDTO.of(boletim);
    }

    private void validate(BoletimDTO boletimDTO) {
        LOGGER.info("Validando boletim");

        if (boletimDTO == null) {
            throw new IllegalArgumentException("BoletimDTO não deve ser nulo");
        }

        if (StringUtils.isEmpty(boletimDTO.getAlunoId())) {
            throw new IllegalArgumentException("AlunoId não deve ser nulo");
        }

        if (StringUtils.isEmpty(boletimDTO.getPeriodoId())) {
            throw new IllegalArgumentException("PeriodoId não deve ser nulo");
        }
    }


    public BoletimDTO findById(Long id) {
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);

        if (boletimOptional.isPresent()) {
            return BoletimDTO.of(boletimOptional.get());
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public Boletim findByBoletimId (Long id){
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);
        if (boletimOptional.isPresent()) {
            return boletimOptional.get();
        }
        throw new IllegalArgumentException(String.format("Boletim %s não está cadastrada", id));
    }


    public BoletimDTO update(BoletimDTO boletimDTO, Long id) {
        Optional<Boletim> boletimExistenteOptional = this.iBoletimRepository.findById(id);

        if (boletimExistenteOptional.isPresent()) {
            Boletim boletimExistente = boletimExistenteOptional.get();

            LOGGER.info("Atualizando boletim... id: [{}]", boletimExistente.getId());
            LOGGER.debug("Payload: {}", boletimDTO);
            LOGGER.debug("Boletim Existente: {}", boletimExistente);

            boletimExistente.setAlunoId(alunoService.findByAlunoId(boletimDTO.getAlunoId()));
            boletimExistente.setPeriodoId(periodoService.findByPeriodoId(boletimDTO.getPeriodoId()));

            boletimExistente = this.iBoletimRepository.save(boletimExistente);

            return BoletimDTO.of(boletimExistente);
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para boletim de ID: [{}]", id);

        this.iBoletimRepository.deleteById(id);
    }
}
