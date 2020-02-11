package br.com.hbsis.ecolahb.boletim;


import br.com.hbsis.ecolahb.aluno.Aluno;
import br.com.hbsis.ecolahb.periodo.Periodo;

import javax.persistence.*;

@Entity
@Table(name = "boletim")
public
class Boletim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "periodo_id", referencedColumnName = "id")
    private Periodo periodoId;
    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno alunoId;


    public Boletim() {
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "id=" + id +
                ", periodoId=" + periodoId +
                ", alunoId=" + alunoId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Periodo getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Periodo periodoId) {
        this.periodoId = periodoId;
    }

    public Aluno getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Aluno alunoId) {
        this.alunoId = alunoId;
    }
}



