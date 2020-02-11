package br.com.hbsis.ecolahb.boletim;


public class BoletimDTO {

    private Long id;
    private Long alunoId;
    private Long periodoId;


    public BoletimDTO(Long id, Long alunoId, Long periodoId) {
        this.id = id;
        this.alunoId = alunoId;
        this.periodoId = periodoId;
    }

    public static BoletimDTO of(Boletim boletim) {
        return new BoletimDTO(
                boletim.getId(),
                boletim.getAlunoId().getId(),
                boletim.getPeriodoId().getId()
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
}
