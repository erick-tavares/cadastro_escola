package br.com.hbsis.ecolahb.periodo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
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

//    public String DateTimeFormatter (LocalDate periodo){
//        String dataFormatada =  (periodo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//        return dataFormatada;
//    }

    private void validate(PeriodoDTO periodoDTO) {
        LOGGER.info("Validando Periodo");

        if (periodoDTO == null) throw new IllegalArgumentException("PeriodoDTO não deve ser nulo");

        if (StringUtils.isEmpty(periodoDTO.getDtInicio())) throw new IllegalArgumentException("DtInicio não deve ser nula");

        if (StringUtils.isEmpty(periodoDTO.getDtFim())) throw new IllegalArgumentException("DtFim não deve ser nula");

        if (StringUtils.isEmpty(periodoDTO.getDescricao())) throw new IllegalArgumentException("Descricao não deve ser nula");

        if ((periodoDTO.getDtFim().isBefore(periodoDTO.getDtInicio())) | periodoDTO.getDtFim().isEqual(periodoDTO.getDtInicio())) {
            throw new IllegalArgumentException("A data do fim do período é invalida");
        }
        List<Periodo> periodoList = this.iPeriodoRepository.findAll();
        for (Periodo periodo : periodoList) {
            if ((periodoDTO.getDtInicio().isBefore(periodo.getDtFim())) && periodoDTO.getDtFim().isAfter(periodo.getDtFim()) ||
            periodoDTO.getDtInicio().isEqual(periodo.getDtFim())){
                throw new IllegalArgumentException("A data de início de período já existe entre outro período");
            }
            if ((periodoDTO.getDtInicio().isBefore(periodo.getDtInicio())) && periodoDTO.getDtFim().isAfter(periodo.getDtInicio()) ||
                    periodoDTO.getDtFim().isEqual(periodo.getDtInicio())){
                throw new IllegalArgumentException("A data de fim de período já existe entre outro período");
            }
            if ((periodoDTO.getDtInicio().isBefore(periodo.getDtInicio())) && periodoDTO.getDtFim().isAfter(periodo.getDtFim())){
                throw new IllegalArgumentException("A data de período já existe entre outro período");
            }
            if ((periodoDTO.getDtInicio().isEqual(periodo.getDtInicio())) && periodoDTO.getDtFim().isEqual(periodo.getDtFim())){
                throw new IllegalArgumentException("O período já existe");
            }
        }
    }


    public List<PeriodoDTO> findAll(){
        List<Periodo> periodoList = iPeriodoRepository.findAll();
        List<PeriodoDTO> periodoDTOList = new ArrayList<>();
        for(Periodo periodo : periodoList){
            periodoDTOList.add(PeriodoDTO.of(periodo));
        }
        return periodoDTOList;
    }

    public PeriodoDTO findById(Long id) {
        Optional<Periodo> periodoOptional = this.iPeriodoRepository.findById(id);

        if (periodoOptional.isPresent()) {
            return PeriodoDTO.of(periodoOptional.get());
        }

        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public Periodo findByPeriodoId(Long id) {
        Optional<Periodo> periodoOptional = this.iPeriodoRepository.findById(id);

        if (periodoOptional.isPresent()) {
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
