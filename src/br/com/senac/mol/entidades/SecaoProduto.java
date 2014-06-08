package br.com.senac.mol.entidades;

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
@Table(name = "secao_produto")
public class SecaoProduto
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_secaoproduto")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_secao")
	private Secao secao;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Produto getProduto()
	{
		return produto;
	}

	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	public Secao getSecao()
	{
		return secao;
	}

	public void setSecao(Secao secao)
	{
		this.secao = secao;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objSecaoProduto)
	{
		if (!(objSecaoProduto instanceof SecaoProduto)) {
			return false;
		}
		SecaoProduto secaoProduto = (SecaoProduto) objSecaoProduto;
		return (this.id != null || secaoProduto.id == null) && (this.id == null || this.id.equals(secaoProduto.id));
	}
}
