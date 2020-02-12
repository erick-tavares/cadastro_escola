package br.com.hbsis.ecolahb.periodo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PeriodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoService.class);

    private final IPeriodoRepository iPeriodoRepository;

    public PeriodoService(IPeriodoRepository iPeriodoRepository) {
        this.iPeriodoRepository = iPeriodoRepository;
    }

    public PeriodoDTO save(PeriodoDTO periodoDTO) {

        this.validate(periodoDTO);

        LOGGER.info("Salvando periodo");
        LOGGER.debug("Periodo: {}", periodoDTO);

        Periodo periodo = new Periodo();
        periodo.setDtInicio(periodoDTO.getDtInicio());
        periodo.setDtFim(periodoDTO.getDtFim());
        periodo.setDescricao(periodoDTO.getDescricao());

        periodo = this.iPeriodoRepository.save(periodo);

        return PeriodoDTO.of(periodo);
    }

    private void validate(PeriodoDTO periodoDTO) {
        LOGGER.info("Validando Periodo");

        if (periodoDTO == null) {
            throw new IllegalArgumentException("PeriodoDTO não deve ser nulo");
        }

        if (StringUtils.isEmpty(periodoDTO.getDtInicio())) {
            throw new IllegalArgumentException("DtInicio não deve ser nula");
        }

        if (StringUtils.isEmpty(periodoDTO.getDtFim())) {
            throw new IllegalArgumentException("DtFim não deve ser nula");
        }
        if (StringUtils.isEmpty(periodoDTO.getDescricao())) {
            throw new IllegalArgumentException("Descricao não deve ser nula");
        }
    }

    public PeriodoDTO findById(Long id) {
        Optional<Periodo> periodoOptional = this.iPeriodoRepository.findById(id);

        if (periodoOptional.isPresent()) {
            return PeriodoDTO.of(periodoOptional.get());
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public Periodo findByPeriodoId (Long id){
        Optional<Periodo> periodoOptional = this.iPeriodoRepository.findById(id);

        if(periodoOptional.isPresent()){
            return periodoOptional.get();
        }
        throw new IllegalArgumentException(String.format("Período %s não está cadastrado", id));
    }

    public PeriodoDTO update(PeriodoDTO periodoDTO, Long id) {
        Optional<Periodo> periodoExistenteOptional = this.iPeriodoRepository.findById(id);

        if (periodoExistenteOptional.isPresent()) {
            Periodo periodoExistente = periodoExistenteOptional.get();

            LOGGER.info("Atualizando periodo... id: [{}]", periodoExistente.getId());
            LOGGER.debug("Payload: {}", periodoDTO);
            LOGGER.debug("Periodo Existente: {}", periodoExistente);

            periodoExistente.setDtInicio(periodoDTO.getDtInicio());
            periodoExistente.setDtFim(periodoDTO.getDtFim());
            periodoExistente.setDescricao(periodoDTO.getDescricao());

            periodoExistente = this.iPeriodoRepository.save(periodoExistente);

            return PeriodoDTO.of(periodoExistente);
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete para periodo de ID: [{}]", id);

        this.iPeriodoRepository.deleteById(id);
    }
}
