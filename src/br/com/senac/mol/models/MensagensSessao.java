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

	/**
	 * Adiciona uma nova mensagem a lista de mensagens.
	 * @param nome O nome da nova mensagem. Seu valor será empty.
	 */
	public void add(String nome)
	{
		add(nome, "");
	}

	/**
	 * Adiciona uma nova mensagem a lista de mensagens.
	 * @param nome O nome da nova mensagem.
	 * @param valor O valor da nova mensagem.
	 */
	public void add(String nome, String valor)
	{
		lista.add(new Mensagem(nome, valor));
	}

	/**
	 * Remove uma mensagem da lista de mensagens pelo nome.
	 * @param nome O nome da mensagem a ser removida.
	 */
	public void remove(String nome)
	{
		for (Mensagem m : lista) {
			if (m.nome.equals(nome)) {
				lista.remove(m);
				break;
			}
		}
	}

	/**
	 * Retorna e remove uma mensagem da lista de mensagens pelo nome.
	 * @param nome O nome da mensagem a ser removida e retornada.
	 * @return O valor da mensagem que foi removida.
	 */
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

	/**
	 * Verifica se existe uma mensagem pelo nome.
	 * @param nome O nome da mensagem.
	 * @return True se a mensagem existe, caso contrario, false.
	 */
	public boolean contem(String nome)
	{
		for (Mensagem m : lista)
			if (m.nome.equals(nome))
				return true;
		return false;
	}

	/**
	 * Retorna o valor de uma mensagem.
	 * @param nome O nome da mensagem.
	 * @return O valor da mensagem.
	 */
	public String get(String nome)
	{
		for (Mensagem m : lista)
			if (m.nome.equals(nome))
				return m.valor;
		return null;
	}

	/**
	 * Verifica se a lista de mensagens esta vazia.
	 * @return True se a lista de mensagens estiver vazia, caso contrario, false.
	 */
	public boolean isEmpty()
	{
		return lista.isEmpty();
	}

	/**
	 * Remove todas as mensagens da lista de mensagens.
	 */
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
