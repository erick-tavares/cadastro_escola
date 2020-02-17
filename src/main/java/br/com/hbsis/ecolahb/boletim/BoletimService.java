package br.com.hbsis.ecolahb.boletim;


import br.com.hbsis.ecolahb.aluno.Aluno;
import br.com.hbsis.ecolahb.aluno.AlunoService;
import br.com.hbsis.ecolahb.materia.Materia;
import br.com.hbsis.ecolahb.materia.MateriaService;
import br.com.hbsis.ecolahb.nota.Nota;
import br.com.hbsis.ecolahb.nota.NotaDTO;
import br.com.hbsis.ecolahb.nota.NotaService;
import br.com.hbsis.ecolahb.periodo.PeriodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoletimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimService.class);

    private final IBoletimRepository iBoletimRepository;
    private final AlunoService alunoService;
    private final PeriodoService periodoService;
    private final MateriaService materiaService;
    public final NotaService notaService;

    public BoletimService(IBoletimRepository iBoletimRepository, AlunoService alunoService, PeriodoService periodoService, MateriaService materiaService, NotaService notaService) {
        this.iBoletimRepository = iBoletimRepository;
        this.alunoService = alunoService;
        this.periodoService = periodoService;
        this.materiaService = materiaService;
        this.notaService = notaService;
    }

    public BoletimDTO save(BoletimDTO boletimDTO) {

        this.validate(boletimDTO);

        LOGGER.info("Salvando boletim");
        LOGGER.debug("Boletim: {}", boletimDTO);

        Boletim boletim = new Boletim();
        boletim.setAlunoId(alunoService.findByAlunoId(boletimDTO.getAlunoId()));
        boletim.setPeriodoId(periodoService.findByPeriodoId(boletimDTO.getPeriodoId()));
        boletim.setNotaList(preencherNotas(boletimDTO.getNotaDTOList()));

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

    public Boletim findByBoletimId(Long id) {
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);
        if (boletimOptional.isPresent()) {
            return boletimOptional.get();
        }
        throw new IllegalArgumentException(String.format("Boletim %s não está cadastrada", id));
    }

    public List<Boletim> findBoletimByAluno(Aluno aluno) {
        List<Boletim> boletimList = this.iBoletimRepository.findByAlunoId(aluno);

        return boletimList;
    }


    public List<BoletimDTO> boletimDoAluno(Long id) {
        Aluno alunoExistente = alunoService.findByAlunoId(id);

        List<BoletimDTO> boletimDTOList = new ArrayList<>();
        for (Boletim boletim : iBoletimRepository.findByAlunoId(alunoExistente)) {
            boletimDTOList.add(BoletimDTO.of(boletim));
        }
        return boletimDTOList;
    }

    public List<Nota> preencherNotas(List<NotaDTO> notaDtoEntrada) {
        List<Nota> notaList = new ArrayList<>();
        for (NotaDTO notaDto : notaDtoEntrada) {
            Nota notaNova = new Nota();

            notaNova.setNota(notaDto.getNota());
            notaNova.setMateriaId(materiaService.findByMateriaId(notaDto.getMateriaId()));
            notaNova.setAlunoId(alunoService.findByAlunoId(notaDto.getAlunoId()));

            notaList.add(notaNova);
        }
        return notaList;
    }

    public List<BoletimModel> preencherBoletim(Long boletimId) {
        List<BoletimModel> boletimModelList = new ArrayList<>();
        Boletim boletimExistente = this.findByBoletimId(boletimId);

        List<Materia> materiaList = materiaService.findAll();
        for (Materia materia : materiaList) {
            List<Nota> notaList = notaService.findAllByAlunoId_IdAndPeriodoId_Id(boletimExistente.getAlunoId().getId(), boletimExistente.getPeriodoId().getId());
            try {
                BoletimModel boletimModel = new BoletimModel();

                double[] notas = new double[4];
                int contador = 0;
                for (int i = 0; i < notaList.size(); i++) {
                    if (notaList.get(i).getMateriaId().getId().equals(materia.getId())) {
                        notas[contador] = notaList.get(i).getNota();
                        contador++;
                    }
                }
                double nota1 = notas [0];
                double nota2 = notas [1];
                double nota3 = notas [2];
                double nota4 = notas [3];

                double media = ((nota1 + nota2 + nota3 + nota4) / notas.length);

                boletimModel.setAluno(this.findByBoletimId(boletimId).getAlunoId().getNome());
                boletimModel.setMateria(materiaService.findByMateriaId(materia.getId()).getNome());

                boletimModel.setPeriodo(this.findByBoletimId(boletimId).getPeriodoId().getDescricao());
                boletimModel.setNota1(String.valueOf(nota1));
                boletimModel.setNota2(String.valueOf(nota2));
                boletimModel.setNota3(String.valueOf(nota3));
                boletimModel.setNota4(String.valueOf(nota4));

                boletimModel.setMedia(String.valueOf(media));
                boletimModelList.add(boletimModel);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return boletimModelList;
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

            boletimExistente.setNotaList(preencherNotas(boletimDTO.getNotaDTOList()));

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
