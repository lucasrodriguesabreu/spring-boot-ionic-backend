package br.com.lucas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	
}
