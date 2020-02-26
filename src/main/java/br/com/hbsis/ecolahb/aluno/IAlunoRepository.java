package br.com.hbsis.ecolahb.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {
}
