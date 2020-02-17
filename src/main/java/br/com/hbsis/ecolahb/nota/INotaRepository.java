package br.com.hbsis.ecolahb.nota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findAllByBoletimId_AlunoId_IdAndBoletimId_PeriodoId_Id(Long alunoId, Long periodoId);

    List<Nota> findAllByAlunoId_Id(Long alunoId);
}
