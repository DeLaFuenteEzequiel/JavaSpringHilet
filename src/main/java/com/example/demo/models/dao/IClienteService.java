package com.example.demo.models.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public void save(Cliente cliente);
	public void deleteById(Long id);
	public Page<Cliente> FindAll(Pageable pageable);
}
