package dev.jpedrosnts.portifolio.repository;

import dev.jpedrosnts.portifolio.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CertificadoRepository extends JpaRepository<Certificado, UUID> {
    List<Certificado> findCertificadoByTecnologiaId(UUID tecnologiaId);
}
