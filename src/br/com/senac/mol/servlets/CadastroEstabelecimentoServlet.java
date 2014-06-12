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
import br.com.senac.mol.entidades.Localizacao;
import br.com.senac.mol.models.MensagensSessao;
import br.com.senac.mol.persistencia.EstabelecimentoDAO;
import br.com.senac.mol.persistencia.LocalizacaoDAO;

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
		String strLatitude = request.getParameter("txtLatitude").trim();
		String strLongitude = request.getParameter("txtLongitude").trim();
		Long latitude = null;
		Long longitude = null;
		Localizacao localizacao = new Localizacao();

		HttpSession sessao = request.getSession();
		MensagensSessao mensagens = new MensagensSessao();
		sessao.setAttribute("mensagens", mensagens);

		// verificando entradas do usuario:
		String erro = "";
		boolean localizacaoOk = true;
		try {
			latitude = Long.parseLong(strLatitude);
			longitude = Long.parseLong(strLongitude);
		} catch (Exception ex) {
			localizacaoOk = false;
		}

		boolean ok = true;
		if (nome.length() < 4) { // nome com no minimo 3 caracteres.
			ok = false;
			erro = "Nome deve ter 3 ou mais caracteres";
		} else if (!localizacaoOk) {
			ok = false;
			erro = "Localização inválida";
		}

		if (!ok) {
			mensagens.add("erro", erro);
			mensagens.add("nome", nome);
			response.sendRedirect(request.getContextPath() + "/cadastro_estabelecimento.jsp");
			return;
		}

		LocalizacaoDAO localizacaoDao = new LocalizacaoDAO();
		localizacao = localizacaoDao.getByEndereco(latitude, longitude);
		if (localizacao == null) {
			localizacao = new Localizacao();
			localizacao.setLatitude(latitude);
			localizacao.setLongitude(longitude);
			localizacaoDao.insert(localizacao);
		}

		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome(nome);
		estabelecimento.setEndereco(localizacao);
		estabelecimento.setDataCadastro(new Date());

		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		dao.insert(estabelecimento);

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
}
