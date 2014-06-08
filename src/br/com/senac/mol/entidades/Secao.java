package br.com.senac.mol.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "secao")
public class Secao
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_secao")
	private Long id;

	@Column(name = "nome", length = 60)
	private String nome;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objSecao)
	{
		if (!(objSecao instanceof Secao)) {
			return false;
		}
		Secao secao = (Secao) objSecao;
		return (this.id != null || secao.id == null) && (this.id == null || this.id.equals(secao.id));
	}
}
