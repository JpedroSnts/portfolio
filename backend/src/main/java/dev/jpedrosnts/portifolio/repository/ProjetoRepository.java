package dev.jpedrosnts.portifolio.repository;

import dev.jpedrosnts.portifolio.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {
    List<Projeto> findByTipoProjetoId(UUID id);
}
