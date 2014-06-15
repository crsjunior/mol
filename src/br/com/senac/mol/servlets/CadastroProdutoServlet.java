package br.com.senac.mol.servlets;

import br.com.senac.mol.entidades.Imagem;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.senac.mol.entidades.Produto;
import br.com.senac.mol.entidades.Usuario;
import br.com.senac.mol.models.MensagensSessao;
import br.com.senac.mol.persistencia.ImagemDAO;
import br.com.senac.mol.persistencia.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CadastroProdutoServlet")
public class CadastroProdutoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CadastroProdutoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String descricao = request.getParameter("txtDescricao").trim();
        String resenha = request.getParameter("txtResenha").trim();
        String strPreco = request.getParameter("txtPreco").replace(".", "").replace(",", ".").trim();
        String hash = request.getParameter("hash");
        String[] imagens = request.getParameterValues("img");

        BigDecimal preco = null;
        Usuario usuario;

        HttpSession sessao = request.getSession();
        MensagensSessao mensagens = new MensagensSessao();
        sessao.setAttribute("mensagens", mensagens);
        usuario = (Usuario) sessao.getAttribute("usuario");

        // verificando entradas do usuario:
        String erro = "";
        boolean precoOk = true;
        try {
            preco = BigDecimal.valueOf(Float.parseFloat(strPreco));
        } catch (Exception ex) {
            precoOk = false;
        }

        boolean ok = true;
        if (descricao.length() < 4) { // descricao com no minimo 3 caracteres.
            ok = false;
            erro = "Descri&ccedil;&atilde;o deve ter 3 ou mais caracteres";
        } else if (!precoOk) {
            ok = false;
            erro = "Valor para o preço inv&aacute;lido";
            erro = "Descri&ccedil;&atilde;o deve ter 3 ou mais caracteres";
        } else if (!precoOk) {
            ok = false;
            erro = "Valor para o preço inv&aacute;lido";
        }

        if (!ok) {
            mensagens.add("erro", erro);
            mensagens.add("descricao", descricao);
            mensagens.add("resenha", resenha);
            mensagens.add("preco", strPreco);
            response.sendRedirect(request.getContextPath() + "/cadastro_produto.jsp");
            return;
        }

        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setResenha(resenha);
        produto.setPreco(preco);
        produto.setDataCadastro(new Date());
        produto.setDataUltimaAtualizacao(new Date());
        produto.setUsuario(usuario);
        
        List<Imagem> listaImagens = new ArrayList<Imagem>();
        if(imagens.length>0){
            for(String nmImg : imagens) {
                Imagem imagem = new Imagem();
                imagem.setArquivo(nmImg);
                imagem.setDataCadastro(new Date());
                imagem.setIdHashImagem(hash);
                listaImagens.add(imagem);
            }
        }
        
        if(listaImagens.size()>0) {
            produto.setImagem(listaImagens);
        }

        ProdutoDAO dao = new ProdutoDAO();
        dao.insert(produto);

        ImagemDAO daoImg = new ImagemDAO();
        for(Imagem img : listaImagens) {
            img.setProduto(produto);
            daoImg.insert(img);
        }
        
        
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}
