package br.com.lucas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
