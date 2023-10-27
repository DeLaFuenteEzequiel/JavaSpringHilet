package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.entity.Cliente;

import com.example.demo.models.dao.IClienteService;

import jakarta.validation.Valid;

@Controller 
public class ClienteController {
	
	@Autowired
	private IClienteService clienteDao;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	
	@GetMapping("/form")
	public String crear(Map<String,Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente",cliente);
		model.put("titulo","Ingreso de datos");
		return "form";
		
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente,BindingResult resultado,Model model,@RequestParam("file")MultipartFile foto) {
		if(resultado.hasErrors()) {
			model.addAttribute("titulo","Formulario de cliente");
			return "form";
		}
		if(!foto.isEmpty()) {
			if(cliente.getId()!=null &&cliente.getId()>0 && cliente.getFoto()!=null && cliente.getFoto().length()>0) {
				Path routPath= Paths.get("uploads").resolve(cliente.getFoto()).toAbsolutePath();
				File archivo=routPath.toFile();
				if(archivo.exists() && archivo.canRead()) {
					archivo.delete();
				}
			}
			String uniqueFileMare=UUID.randomUUID().toString()+"-"+foto.getOriginalFilename();
			Path routPath=Paths.get("uploads").resolve(uniqueFileMare);
			Path routAbsolute = routPath.toAbsolutePath();
			
			try {
				Files.copy(foto.getInputStream(),routAbsolute);
				cliente.setFoto(uniqueFileMare);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		clienteDao.save(cliente);
		return "redirect:/listar";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id,Map<String,Object> model) {
		Cliente cliente = null;
		if(id>0) {
			cliente=clienteDao.findOne(id);
		}
		else{
			return "redirect:/listar";
		}
		model.put("cliente",cliente);
		model.put("titulo","Editar Cliente");
		return "form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		if(id>0) {
			clienteDao.delete(id);
		}
		return "redirect:/listar";
	}
	
	@GetMapping("/ver{id}")
	public String ver(@PathVariable(value="id")Long id, Map<String,Object> model) {
		Cliente cliente = clienteDao.findOne(id);
		if(cliente==null) {
			return "redirect:/listar";
		}
		model.put("cliente",cliente);
		model.put("titulo","Detalle Cliente");
		return "ver";
		
	}
	
}
