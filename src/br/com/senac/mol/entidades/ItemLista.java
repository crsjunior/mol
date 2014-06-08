package br.com.senac.mol.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_lista")
public class ItemLista
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_itemlista")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_lista")
	private Lista lista;

	@Column(name = "preco", precision = 15, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Lista getLista()
	{
		return lista;
	}

	public void setLista(Lista lista)
	{
		this.lista = lista;
	}

	public BigDecimal getPreco()
	{
		return preco;
	}

	public void setPreco(BigDecimal preco)
	{
		this.preco = preco;
	}

	public Produto getProduto()
	{
		return produto;
	}

	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objItemLista)
	{
		if (!(objItemLista instanceof ItemLista)) {
			return false;
		}
		ItemLista itemLista = (ItemLista) objItemLista;
		return (this.id != null || itemLista.id == null) && (this.id == null || this.id.equals(itemLista.id));
	}
}
