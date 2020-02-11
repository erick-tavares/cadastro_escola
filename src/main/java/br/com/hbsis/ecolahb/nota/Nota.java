package br.com.hbsis.ecolahb.nota;

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
    @JoinColumn(name = "boletim_id", referencedColumnName = "id")
    private Boletim boletimId;

    public Nota() {
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", nota=" + nota +
                ", materiaId=" + materiaId +
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

    public Boletim getBoletimId() {
        return boletimId;
    }

    public void setBoletimId(Boletim boletimId) {
        this.boletimId = boletimId;
    }
}
