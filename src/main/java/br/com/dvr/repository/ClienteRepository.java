package br.com.dvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dvr.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	

}
