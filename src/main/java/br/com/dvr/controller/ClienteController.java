package br.com.dvr.controller;

import java.util.List;

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

import br.com.dvr.model.Cliente;
import br.com.dvr.service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {
	
	//Injeção de dependência
		
		private final ClienteService clienteService;
		
		@Autowired
		public ClienteController(ClienteService clienteService){
			this.clienteService = clienteService;
		}
		
		@RequestMapping( value = "/{id}", method= RequestMethod.GET)
		public ResponseEntity<Cliente> getClientePorID(@PathVariable("id") Long idCliente) {
			Cliente cliente = clienteService.getClientePorId(idCliente);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		
		@RequestMapping(value = "", method= RequestMethod.GET)
		public ResponseEntity<List<Cliente>> getTodosClientes() {
			List<Cliente> listaClientes = clienteService.getTodosClientes();
			return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
		}
		
		@RequestMapping(value = "", method= RequestMethod.POST)
		public ResponseEntity<Void> cadastrarCliente(@RequestBody Cliente clienteP, 
			UriComponentsBuilder uriComponentsBuilder) {

			final Cliente cliente = clienteService.addCliente(clienteP);
			
			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uriComponentsBuilder.path("/clientes/{id}")
				.buildAndExpand(cliente.getIdCliente()).toUri());

			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		}
		
		@RequestMapping( value = "/{id}", method= RequestMethod.PUT)
		public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
			clienteService.alterarCliente(cliente, id);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		
		@RequestMapping( value = "/{id}", method= RequestMethod.DELETE)
		public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id) {
			clienteService.deletarCliente(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}	

}
