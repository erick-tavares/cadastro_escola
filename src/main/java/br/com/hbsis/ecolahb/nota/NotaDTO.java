package br.com.hbsis.ecolahb.nota;


public class NotaDTO {

    private Long id;
    private double nota;
    private Long materiaId;
    private Long boletimId;

    public NotaDTO(Long id, double nota, Long materiaId, Long boletimId) {
        this.id = id;
        this.nota = nota;
        this.materiaId = materiaId;
        this.boletimId = boletimId;
    }

    public static NotaDTO of(Nota nota) {
        return new NotaDTO(
                nota.getId(),
                nota.getNota(),
                nota.getMateriaId().getId(),
                nota.getBoletimId().getId()
        );
    }

    @Override
    public String toString() {
        return "NotaDTO{" +
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

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public Long getBoletimId() {
        return boletimId;
    }

    public void setBoletimId(Long boletimId) {
        this.boletimId = boletimId;
    }
}
