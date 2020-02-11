package br.com.hbsis.ecolahb.periodo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPeriodoRepository extends JpaRepository<Periodo, Long> {
}
