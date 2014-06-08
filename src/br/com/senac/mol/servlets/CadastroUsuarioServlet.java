package br.com.senac.mol.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.mol.entidades.Usuario;
import br.com.senac.mol.models.Constantes;
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
		
		boolean tudoOk = (txtNome.length() > 2 && txtEmail.matches(Constantes.REGEX_EMAIL) &&
				txtSenha.length() > 5 && txtSenha.equals(txtSenhaConfirma));
		
		if (!tudoOk) {
			response.sendRedirect(request.getContextPath() + "/cadastro_usuario.jsp?erro");
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
