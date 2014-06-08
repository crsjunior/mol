package br.com.senac.mol.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "localizacao")
public class Localizacao
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_localizacao")
	private Long id;

	@Column(name = "latitude")
	private Long latitude;

	@Column(name = "longitude")
	private Long longitude;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getLatitude()
	{
		return latitude;
	}

	public void setLatitude(Long latitude)
	{
		this.latitude = latitude;
	}

	public Long getLongitude()
	{
		return longitude;
	}

	public void setLongitude(Long longitude)
	{
		this.longitude = longitude;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object objLocalizacao)
	{
		if (!(objLocalizacao instanceof Localizacao)) {
			return false;
		}
		Localizacao localizacao = (Localizacao) objLocalizacao;
		return (this.id != null || localizacao.id == null) && (this.id == null || this.id.equals(localizacao.id));
	}
}
