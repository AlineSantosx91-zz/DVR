package br.com.dvr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dvr.model.Imovel;
import br.com.dvr.repository.ImovelRepository;
import br.com.dvr.validators.Result;
import br.com.dvr.validators.Validator;

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

	public ResponseEntity<Result<Imovel>> addImovel(Imovel imovelRequest) {

		List<Validator> validators = new ArrayList<Validator>();

		if (imovelRequest.getX() == null || imovelRequest.getY() == null) {
			validators.add(new Validator("Campos de delimitaçao devem ser preenchidos"));
			return new ResponseEntity<Result<Imovel>>(new Result<Imovel>(0, validators), HttpStatus.OK);
		}

		if (imovelRequest.getX() < 0 || imovelRequest.getX() > 1400) {
			validators.add(new Validator("Valor do campo X inválido"));
		}

		if (imovelRequest.getY() < 0 || imovelRequest.getY() > 1000) {
			validators.add(new Validator("Valor do campo Y inválido"));
		}

		if (imovelRequest.getBeds() == null || imovelRequest.getBeds() < 1 || imovelRequest.getBeds() > 5) {
			validators.add(new Validator("Quantidade de quarto deve ser no mínimo 1 e no máximo 5"));
		}

		if (imovelRequest.getBaths() == null || imovelRequest.getBaths() < 1 || imovelRequest.getBaths() > 4) {
			validators.add(new Validator("Quantidade de quarto deve ser no mínimo 1 e no máximo 4"));
		}

		if (imovelRequest.getSquareMeters() == null || imovelRequest.getSquareMeters() < 30 || imovelRequest.getSquareMeters() > 240) {
			validators.add(new Validator("Metragem inválida, máximo 240 metros quadrados, e no mínimo 20"));
		}

		if (validators.size() > 0) {
			return new ResponseEntity<Result<Imovel>>(new Result<Imovel>(0, validators), HttpStatus.OK);
		}

		Result<Imovel> imovel = new Result<Imovel>(imovelRepository.save(imovelRequest));
		return new ResponseEntity<Result<Imovel>>(imovel, HttpStatus.CREATED);
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
