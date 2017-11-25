package br.com.dvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dvr.model.Imovel;


public interface ImovelRepository extends JpaRepository<Imovel, Long>{
	

}
