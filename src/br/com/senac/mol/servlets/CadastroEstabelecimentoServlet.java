package br.com.senac.mol.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.senac.mol.entidades.Estabelecimento;
import br.com.senac.mol.entidades.Endereco;
import br.com.senac.mol.models.MensagensSessao;
import br.com.senac.mol.persistencia.EstabelecimentoDAO;
import br.com.senac.mol.persistencia.EnderecoDAO;

@WebServlet("/CadastroEstabelecimentoServlet")
public class CadastroEstabelecimentoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CadastroEstabelecimentoServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nome = request.getParameter("txtNome").trim();
		String endereco = request.getParameter("txtEndereco").trim();
                String numero = request.getParameter("txtNumero").trim();
                String bairro = request.getParameter("txtBairro").trim();
                String cidade = request.getParameter("txtCidade").trim();
                String uf = request.getParameter("txtUF").trim();
		
		HttpSession sessao = request.getSession();
		MensagensSessao mensagens = new MensagensSessao();
		sessao.setAttribute("mensagens", mensagens);

		EnderecoDAO enderecoDao = new EnderecoDAO();
		Endereco end = new Endereco();
                end.setEndereco(endereco);
                end.setNumero(numero);
                end.setBairro(bairro);
                end.setCidade(cidade);
                end.setUf(uf);
                enderecoDao.insert(end);
                
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome(nome);
		estabelecimento.setEndereco(end);
		estabelecimento.setDataCadastro(new Date());

		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		dao.insert(estabelecimento);

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
}
