package com.example.demo.models.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ClienteDAOImp implements IClienteDAO {
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> FindAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void Save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId()> 0) {
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente FindOne(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	@Transactional
	public void Delete(Long id) {
		Cliente cliente = FindOne(id);
		em.remove(cliente);
	}

}
