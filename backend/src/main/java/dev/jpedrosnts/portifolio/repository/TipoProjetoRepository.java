package dev.jpedrosnts.portifolio.repository;

import dev.jpedrosnts.portifolio.model.TipoProjeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TipoProjetoRepository extends JpaRepository<TipoProjeto, UUID> {
}
