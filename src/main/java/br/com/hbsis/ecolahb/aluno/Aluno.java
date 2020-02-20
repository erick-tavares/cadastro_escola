package br.com.hbsis.ecolahb.aluno;


import javax.persistence.*;

@Entity
@Table(name = "aluno")
public
class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, unique = true, length = 255)
    private String nome;
    @Column(name = "endereco",  nullable = false, length = 255)
    private String endereco;
    @Column(name = "telefone", nullable = false, length = 255)
    private String telefone;

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
