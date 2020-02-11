package br.com.hbsis.ecolahb.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByNome(String nome);
}
