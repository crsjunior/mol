package br.com.senac.mol.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.senac.mol.entidades.Usuario;
import br.com.senac.mol.models.Constantes;
import br.com.senac.mol.models.MensagensSessao;
import br.com.senac.mol.persistencia.UsuarioDAO;

@WebServlet("/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CadastroUsuarioServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String txtNome = request.getParameter("txtNome");
		String txtEmail = request.getParameter("txtEmail");
		String txtSenha = request.getParameter("txtSenha");
		String txtSenhaConfirma = request.getParameter("txtSenhaConfirma");

		HttpSession sessao = request.getSession();
		MensagensSessao mensagens = new MensagensSessao();
		sessao.setAttribute("mensagens", mensagens);

		// verificando entradas do usuario:
		boolean ok = true;
		if (txtNome.length() < 3) { // nome com no minimo de 3 caracteres.
			ok = false;
			mensagens.add("erro", "Nome deve ter 3 ou mais caracteres");
		} else if (!txtEmail.matches(Constantes.REGEX_EMAIL)) { // email com formato valido.
			ok = false;
			mensagens.add("erro", "Email inválido");
		} else if (txtSenha.length() < 6) { // senha com no minimo 6 caracteres.
			ok = false;
			mensagens.add("erro", "Senha deve ter 6 ou mais caracteres");
		} else if (!txtSenha.equals(txtSenhaConfirma)) { // senha e confirmacao da senha iguais.
			ok = false;
			mensagens.add("erro", "Confirmação incorreta de senha");
		}

		if (!ok) {
			mensagens.add("nome", txtNome);
			mensagens.add("email", txtEmail);
			response.sendRedirect(request.getContextPath() + "/cadastro_usuario.jsp");
			return;
		}

		Usuario usuario = new Usuario();
		usuario.setNome(txtNome);
		usuario.setEmail(txtEmail);
		usuario.setSenha(txtSenha);
		usuario.setDataCadastro(new Date());
		usuario.setAtivo((short) 1);

		UsuarioDAO dao = new UsuarioDAO();
		dao.insert(usuario);

		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
}
