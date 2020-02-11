package br.com.hbsis.ecolahb.periodo;


import java.time.LocalDate;

public class PeriodoDTO {

    private Long id;
    private LocalDate dtInicio;
    private LocalDate dtFim;


    public PeriodoDTO(Long id, LocalDate dtInicio, LocalDate dtFim) {
        this.id = id;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }

    public static PeriodoDTO of(Periodo periodo) {
        return new PeriodoDTO(
                periodo.getId(),
                periodo.getDtInicio(),
                periodo.getDtFim()
        );
    }

    @Override
    public String toString() {
        return "PeriodoDTO{" +
                "id=" + id +
                ", dtInicio=" + dtInicio +
                ", dtFim=" + dtFim +
                '}';
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
