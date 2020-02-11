package br.com.hbsis.ecolahb.periodo;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "periodo")
public
class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dt_inicio", nullable = false)
    private LocalDate dtInicio;
    @Column(name = "dt_fim",  nullable = false)
    private LocalDate dtFim;


    public Periodo() {
    }

    @Override
    public String toString() {
        return "Periodo{" +
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
