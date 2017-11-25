package br.com.dvr.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dvr.model.Imovel;
import br.com.dvr.repository.ImovelRepository;
import br.com.dvr.validators.Result;

@Service
public class ImovelService {

	private final ImovelRepository imovelRepository;

	@Autowired
	public ImovelService(ImovelRepository imovelRepository) {
		this.imovelRepository = imovelRepository;
	}

	public Result<Imovel> getImovelPorId(Long idImovel) {
		return new Result<Imovel>(imovelRepository.findOne(idImovel));
	}

	public Result<Imovel> getTodosImoveis() {
		return new Result<Imovel>(imovelRepository.findAll());
	}

	public Result<Imovel> addImovel(Imovel imovel) {
		return new Result<Imovel>(imovelRepository.save(imovel));
	}

	public Result<Imovel> alterarImovel(Imovel imovel, Long id) {
		Imovel existingImovel = imovelRepository.findOne(id);
		BeanUtils.copyProperties(imovel, existingImovel);
		return new Result<Imovel>(imovelRepository.save(imovel));
	}

	public void deletarImovel(Long id) {
		imovelRepository.delete(id);
	}
}
