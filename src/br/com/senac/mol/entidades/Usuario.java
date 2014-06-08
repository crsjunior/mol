package br.com.senac.mol.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario")
public class Usuario
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "nome", length = 60)
	private String nome;

	@Column(name = "email", length = 60)
	private String email;

	@Column(name = "senha", length = 60)
	private String senha;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Column(name = "ativo")
	private short ativo;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public Date getDataCadastro()
	{
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro)
	{
		this.dataCadastro = dataCadastro;
	}

	public short getAtivo()
	{
		return ativo;
	}

	public void setAtivo(short ativo)
	{
		this.ativo = ativo;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
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
	public boolean equals(Object objUsuario)
	{
		if (!(objUsuario instanceof Usuario)) {
			return false;
		}
		Usuario usuario = (Usuario) objUsuario;
		return (this.id != null || usuario.id == null) && (this.id == null || this.id.equals(usuario.id));
	}
}
