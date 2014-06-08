package br.com.senac.mol.models;

import java.util.ArrayList;
import java.util.List;

public class MensagensSessao
{
	private List<Mensagem> lista;

	public MensagensSessao()
	{
		this.lista = new ArrayList<Mensagem>();
	}

	public void add(String nome)
	{
		add(nome, "");
	}

	public void add(String nome, String valor)
	{
		lista.add(new Mensagem(nome, valor));
	}

	public void remove(String nome)
	{
		for (Mensagem m : lista) {
			if (m.nome.equals(nome)) {
				lista.remove(m);
				break;
			}
		}
	}

	public String pop(String nome)
	{
		String retorno = null;
		for (Mensagem m : lista) {
			if (m.nome.equals(nome)) {
				retorno = m.valor;
				lista.remove(m);
				break;
			}
		}
		return retorno;
	}

	public boolean contem(String nome)
	{
		for (Mensagem m : lista)
			if (m.nome.equals(nome))
				return true;
		return false;
	}

	public String get(String nome)
	{
		for (Mensagem m : lista)
			if (m.nome.equals(nome))
				return m.valor;
		return null;
	}

	public boolean isEmpty()
	{
		return lista.isEmpty();
	}

	public void clear()
	{
		lista.clear();
	}


	class Mensagem
	{
		public String nome;
		public String valor;

		Mensagem(String nome, String valor)
		{
			this.nome = nome;
			this.valor = valor;
		}
	}
}
