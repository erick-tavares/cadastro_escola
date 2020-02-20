package br.com.hbsis.ecolahb.boletim;


import br.com.hbsis.ecolahb.nota.NotaDTO;

import java.util.ArrayList;
import java.util.List;

public class BoletimDTO {

    private Long id;
    private Long alunoId;
    private Long periodoId;
    private List<NotaDTO> notaDTOList;


    public BoletimDTO(Long id, Long alunoId, Long periodoId, List<NotaDTO> notaDTOList) {
        this.id = id;
        this.alunoId = alunoId;
        this.periodoId = periodoId;
        this.notaDTOList = notaDTOList;
    }

    public BoletimDTO(Long id, Long alunoId, Long periodoId) {
        this.id = id;
        this.alunoId = alunoId;
        this.periodoId = periodoId;
    }

    public BoletimDTO() {
    }

    public static BoletimDTO of(Boletim boletim) {
        List<NotaDTO> notaDTOList = new ArrayList<>();
if(boletim.getNotaList() != null) {
    boletim.getNotaList().forEach(nota -> notaDTOList.add(NotaDTO.of(nota)));
}
        return new BoletimDTO(
                boletim.getId(),
                boletim.getAlunoId().getId(),
                boletim.getPeriodoId().getId(),
                notaDTOList
        );
    }

    @Override
    public String toString() {
        return "BoletimDTO{" +
                "id=" + id +
                ", alunoId=" + alunoId +
                ", periodoId=" + periodoId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }

    public List<NotaDTO> getNotaDTOList() {
        return notaDTOList;
    }

    public void setNotaDTOList(List<NotaDTO> notaDTOList) {
        this.notaDTOList = notaDTOList;
    }
}
