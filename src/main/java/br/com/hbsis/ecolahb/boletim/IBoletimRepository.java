package br.com.hbsis.ecolahb.boletim;


import br.com.hbsis.ecolahb.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBoletimRepository extends JpaRepository <Boletim, Long>{
    List<Boletim> findByAlunoId(Aluno aluno);
}
