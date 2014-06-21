package br.com.senac.mol.servlets;

import br.com.senac.mol.entidades.Estabelecimento;
import br.com.senac.mol.entidades.ItemLista;
import br.com.senac.mol.entidades.Lista;
import br.com.senac.mol.entidades.Produto;
import br.com.senac.mol.entidades.Usuario;
import br.com.senac.mol.models.MensagensSessao;
import br.com.senac.mol.persistencia.EstabelecimentoDAO;
import br.com.senac.mol.persistencia.ItemListaDAO;
import br.com.senac.mol.persistencia.ListaDAO;
import br.com.senac.mol.persistencia.ProdutoDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CadastroListaServlet")
public class CadastroListaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CadastroListaServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
            String descricao = request.getParameter("txtDescricao");
            String estabelecimento = request.getParameter("estabelecimento");
            String[] precos = request.getParameterValues("preco");
            String[] produtos = request.getParameterValues("id");

            Usuario usuario;

            HttpSession sessao = request.getSession();
            MensagensSessao mensagens = new MensagensSessao();
            sessao.setAttribute("mensagens", mensagens);
            usuario = (Usuario) sessao.getAttribute("usuario");
            
            System.out.println("USUARIO: "+usuario.getNome());
            
            System.out.println("Descrição: "+descricao);
            System.out.println("Estabelecimento: "+estabelecimento);
            System.out.println("Preços: "+precos.length);
            System.out.println("Produtos: "+produtos.length);
            
            ListaDAO dao = new ListaDAO();
            ItemListaDAO itensDao = new ItemListaDAO();
            ProdutoDAO daoProduto = new ProdutoDAO();
            
            Lista novaLista = new Lista();
            EstabelecimentoDAO daoEstab = new EstabelecimentoDAO();
            Estabelecimento estab = daoEstab.getById(Integer.valueOf(estabelecimento));            
            
            novaLista.setDataCadastro(new Date());
            novaLista.setDescricao(descricao);
            novaLista.setEstabelecimento(estab);
            novaLista.setUsuario(usuario);
            
            dao.insert(novaLista);
            for(int i=0; i<precos.length; i++) {
                ItemLista itemLista = new ItemLista();
                itemLista.setLista(novaLista);
                itemLista.setPreco(new BigDecimal(precos[i].replace(",", ".")));
                Produto produto = daoProduto.getById(new Long(produtos[i]));
                itemLista.setProduto(produto);
                itensDao.insert(itemLista);
            }
            
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
            
	}
}
