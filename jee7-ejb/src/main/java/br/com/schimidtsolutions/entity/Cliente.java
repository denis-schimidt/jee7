package br.com.schimidtsolutions.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
	@Id
	@SequenceGenerator(name="ClienteGenerator", sequenceName="CLIENTE_SEQ", schema="c##sysdev", initialValue=1, allocationSize=Integer.MAX_VALUE)
	@GeneratedValue(generator="ClienteGenerator", strategy=GenerationType.SEQUENCE)
	@Min(value=1, message="O id não pode ser inferior a 1.")
	private Integer id;
	
	@NotNull(message="O nome não pode ser nulo.") 
	@Size(min=10, max=50, message="O nome deve ter entre 10 e 50 caracteres.")
	private String nome;
	
	Cliente() {}
	
	public Cliente(final Integer id) {
		this.id = id;
	}

	public Cliente(final String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(final String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return String.format("Cliente [id=%s, nome=%s]", id, nome);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Cliente)) {
			return false;
		}
		final Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}
}
