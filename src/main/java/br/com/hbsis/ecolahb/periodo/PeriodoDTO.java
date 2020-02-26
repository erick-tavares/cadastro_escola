package br.com.hbsis.ecolahb.periodo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PeriodoDTO {

    private Long id;

 // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtInicio;
 @JsonFormat(pattern = "dd/MM/yyyy")
 //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dtFim;
    private String descricao;


    public PeriodoDTO(Long id, LocalDate dtInicio, LocalDate dtFim, String descricao) {
        this.id = id;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.descricao = descricao;
    }

    public PeriodoDTO() {
    }

    public static PeriodoDTO of(Periodo periodo) {
        return new PeriodoDTO(
                periodo.getId(),
                periodo.getDtInicio(),
                periodo.getDtFim(),
                periodo.getDescricao());
    }

    @Override
    public String toString() {
        return "PeriodoDTO{" +
                "id=" + id +
                ", dtInicio=" + dtInicio +
                ", dtFim=" + dtFim +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDate getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDate dtFim) {
        this.dtFim = dtFim;
    }
}
