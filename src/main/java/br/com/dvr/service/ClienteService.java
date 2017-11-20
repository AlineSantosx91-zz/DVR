package br.com.dvr.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dvr.model.Cliente;
import br.com.dvr.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService (ClienteRepository clienteRepository){
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente getClientePorId(Long idCliente){
		return clienteRepository.findOne(idCliente);
	}
	
	public List<Cliente> getTodosClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente addCliente(Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	public Cliente alterarCliente(Cliente cliente, Long id){
		Cliente existingCliente = clienteRepository.findOne(id);
		BeanUtils.copyProperties(cliente, existingCliente);
		return clienteRepository.save(cliente);
	}
	
	public void deletarCliente(Long id){
		clienteRepository.delete(id);
	}
}
