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
    @Column (name = "descricao", nullable = false)
    private String descricao;


    public Periodo() {
    }

    @Override
    public String toString() {
        return "Periodo{" +
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
