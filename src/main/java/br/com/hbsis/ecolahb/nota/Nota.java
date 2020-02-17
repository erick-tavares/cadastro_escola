package br.com.hbsis.ecolahb.nota;

import br.com.hbsis.ecolahb.aluno.Aluno;
import br.com.hbsis.ecolahb.boletim.Boletim;
import br.com.hbsis.ecolahb.materia.Materia;

import javax.persistence.*;

@Entity
@Table(name = "nota")
public
class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nota", nullable = false)
    private double nota;

    @ManyToOne
    @JoinColumn(name = "materia_id", referencedColumnName = "id")
    private Materia materiaId;
    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno alunoId;
    @ManyToOne
    @JoinColumn(name = "boletim_id", referencedColumnName = "id")
    private Boletim boletimId;

    public Nota() {
    }

    public Boletim getBoletimId() {
        return boletimId;
    }

    public void setBoletimId(Boletim boletimId) {
        this.boletimId = boletimId;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", nota=" + nota +
                ", materiaId=" + materiaId +
                ", alunoId=" + alunoId +
                ", boletimId=" + boletimId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Materia getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Materia materiaId) {
        this.materiaId = materiaId;
    }

    public Aluno getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Aluno alunoId) {
        this.alunoId = alunoId;
    }
}
