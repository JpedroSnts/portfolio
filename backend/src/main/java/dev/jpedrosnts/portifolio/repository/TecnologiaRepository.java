package dev.jpedrosnts.portifolio.repository;

import dev.jpedrosnts.portifolio.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, UUID> {
}
