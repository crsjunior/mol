package br.com.senac.mol.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "likes_itens")
public class LikesItens
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_likesitens")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", length = 60)
	private Date dataCadastro;

	@OneToOne
	@JoinColumn(name = "id_itemlista")
	private ItemLista itemLista;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "lk", length = 1, columnDefinition = "CHAR")
	private String lk;

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

	public ItemLista getItemLista()
	{
		return itemLista;
	}

	public void setItemLista(ItemLista itemLista)
	{
		this.itemLista = itemLista;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public String getLk()
	{
		return lk;
	}

	public void setLk(String lk)
	{
		this.lk = lk;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objLikesItens)
	{
		if (!(objLikesItens instanceof LikesItens)) {
			return false;
		}
		LikesItens likesItens = (LikesItens) objLikesItens;
		return (this.id != null || likesItens.id == null) && (this.id == null || this.id.equals(likesItens.id));
	}
}
