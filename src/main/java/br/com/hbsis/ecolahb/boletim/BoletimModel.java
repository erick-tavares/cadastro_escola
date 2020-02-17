package br.com.hbsis.ecolahb.boletim;

public class BoletimModel {

private String aluno;
private String periodo;
private String materia;
private String nota1;
private String nota2;
private String nota3;
private String nota4;
private String media;

    public BoletimModel(String aluno, String periodo, String materia, String nota1, String nota2, String nota3, String nota4, String media) {
        this.aluno = aluno;
        this.periodo = periodo;
        this.materia = materia;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.media = media;
    }


    @Override
    public String toString() {
        return "BoletimModel{" +
                "aluno='" + aluno + '\'' +
                ", periodo='" + periodo + '\'' +
                ", materia='" + materia + '\'' +
                ", nota1='" + nota1 + '\'' +
                ", nota2='" + nota2 + '\'' +
                ", nota3='" + nota3 + '\'' +
                ", nota4='" + nota4 + '\'' +
                ", media='" + media + '\'' +
                '}';
    }

    public BoletimModel() {
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public String getNota4() {
        return nota4;
    }

    public void setNota4(String nota4) {
        this.nota4 = nota4;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
