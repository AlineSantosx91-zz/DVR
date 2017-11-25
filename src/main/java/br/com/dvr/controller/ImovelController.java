package br.com.dvr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dvr.model.Imovel;
import br.com.dvr.service.ImovelService;
import br.com.dvr.validators.Result;

@RestController
@RequestMapping("imoveis")
public class ImovelController {

	private final ImovelService imovelService;

	@Autowired
	public ImovelController(ImovelService imovelService) {
		this.imovelService = imovelService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Result<Imovel>> getImovelPorID(@PathVariable("id") Long idImovel) {
		Result<Imovel> imovel = imovelService.getImovelPorId(idImovel);
		return new ResponseEntity<Result<Imovel>>(imovel, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Result<Imovel>> getTodosImoveis() {
		Result<Imovel> listaImoveis = imovelService.getTodosImoveis();
		return new ResponseEntity<Result<Imovel>>(listaImoveis, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> cadastrarImovel(@RequestBody Imovel imovelP,
			UriComponentsBuilder uriComponentsBuilder) {

		final Result<Imovel> imovel = imovelService.addImovel(imovelP);
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(
				uriComponentsBuilder.path("/imoveis/{id}").buildAndExpand(imovel.getResult().getId()).toUri());

		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Result<Imovel>> alterarImovel(@RequestBody Imovel imovelP, @PathVariable Long id) {
		Result<Imovel> imovel = imovelService.alterarImovel(imovelP, id);
		return new ResponseEntity<Result<Imovel>>(imovel, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarImovel(@PathVariable("id") Long id) {
		imovelService.deletarImovel(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
