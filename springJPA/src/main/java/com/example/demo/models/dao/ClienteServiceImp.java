package com.example.demo.models.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.models.entity.Cliente;


@Service
public class ClienteServiceImp implements IClienteService {
	
	@Autowired
	private IClienteDAO_2 clienteDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteDao.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Cliente findById(Long id) {
		return (Cliente)clienteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
	}
}
