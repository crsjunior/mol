package br.com.senac.mol.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_estabelecimento")
	private Long id;

	@Column(name = "nome", length = 60, nullable = false)
	private String nome;

	@OneToOne
	@JoinColumn(name = "id")
	private Endereco endereco;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

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

	public Date getDataCadastro()
	{
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro)
	{
		this.dataCadastro = dataCadastro;
	}

	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objEstabelecimento)
	{
		if (!(objEstabelecimento instanceof Estabelecimento)) {
			return false;
		}
		Estabelecimento estabelecimento = (Estabelecimento) objEstabelecimento;
		return (this.id != null || estabelecimento.id == null) && (this.id == null || this.id.equals(estabelecimento.id));
	}
}
