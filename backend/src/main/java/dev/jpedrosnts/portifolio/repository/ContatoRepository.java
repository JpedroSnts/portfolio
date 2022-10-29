package dev.jpedrosnts.portifolio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.jpedrosnts.portifolio.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {

}
