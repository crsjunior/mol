package br.com.senac.mol.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "preco_lista")
public class PrecoLista
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_precolista")
	private Long id;

	@Column(name = "preco", precision = 15, scale = 2)
	private BigDecimal preco;

	@OneToOne
	@JoinColumn(name = "id_itemlista")
	private ItemLista itemLista;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public BigDecimal getPreco()
	{
		return preco;
	}

	public void setPreco(BigDecimal preco)
	{
		this.preco = preco;
	}

	public ItemLista getItemLista()
	{
		return itemLista;
	}

	public void setItemLista(ItemLista itemLista)
	{
		this.itemLista = itemLista;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objPrecoLista)
	{
		if (!(objPrecoLista instanceof PrecoLista)) {
			return false;
		}
		PrecoLista precoLista = (PrecoLista) objPrecoLista;
		return (this.id != null || precoLista.id == null) && (this.id == null || this.id.equals(precoLista.id));
	}
}
