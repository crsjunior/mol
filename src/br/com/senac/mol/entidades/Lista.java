package br.com.senac.mol.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lista")
public class Lista
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_lista")
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_estabalecimento")
	private Estabelecimento estabelecimento;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getDataCadastro()
	{
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro)
	{
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Estabelecimento getEstabelecimento()
	{
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento)
	{
		this.estabelecimento = estabelecimento;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objLista)
	{
		if (!(objLista instanceof Lista)) {
			return false;
		}
		Lista lista = (Lista) objLista;
		return (this.id != null || lista.id == null) && (this.id == null || this.id.equals(lista.id));
	}
}
