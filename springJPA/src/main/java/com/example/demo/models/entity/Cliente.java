package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity //Para mapear con una BD
@Table(name="clientes") //Para que la tabla se llame clientes
public class Cliente implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;
	
	@Column(name="nombre")
	private String Nombre;
	
	@Column(name="apellido")
	private String Apellido;
	
	@Column(name="email")
	private String Email;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date CreateAt;
	
	private String Foto;
	
	// Getters y Setters
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getCreateAt() {
		return CreateAt;
	}
	public void setCreateAt(Date createAt) {
		CreateAt = createAt;
	}
	@PrePersist
	public void getPersist() {
		CreateAt = new Date();
	}
	public String getFoto() {
		return Foto;
	}
	public void setFoto(String foto) {
		Foto = foto;
	}
	
}
