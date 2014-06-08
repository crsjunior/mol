package br.com.senac.mol.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.senac.mol.entidades.Usuario;
import br.com.senac.mol.persistencia.UsuarioDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public LoginServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String txtEmail = request.getParameter("txtEmail");
		String txtSenha = request.getParameter("txtSenha");
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario u = dao.getByEmailSenha(txtEmail, txtSenha);

		if (u == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp?erro");
		} else {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuario", u);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
}
