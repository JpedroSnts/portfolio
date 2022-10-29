package dev.jpedrosnts.portifolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.jpedrosnts.portifolio.model.Contato;
import dev.jpedrosnts.portifolio.repository.ContatoRepository;

@Service
public class ContatoService {

    private final ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public Contato salvar(Contato contato) {
        return repository.save(contato);
    }

}
