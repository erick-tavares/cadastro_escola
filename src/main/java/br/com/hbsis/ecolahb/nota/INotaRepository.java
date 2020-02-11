package br.com.hbsis.ecolahb.nota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotaRepository extends JpaRepository<Nota, Long> {
}
