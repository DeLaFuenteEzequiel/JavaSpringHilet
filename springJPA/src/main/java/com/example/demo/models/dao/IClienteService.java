package com.example.demo.models.dao;
import java.util.List;
import com.example.demo.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public void save(Cliente cliente);
	public void deleteById(Long id);
}
