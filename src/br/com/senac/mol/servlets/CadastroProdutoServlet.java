package br.com.senac.mol.servlets;

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
import br.com.senac.mol.persistencia.ProdutoDAO;
import java.io.File;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/CadastroProdutoServlet")
public class CadastroProdutoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CadastroProdutoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        if (ServletFileUpload.isMultipartContent(request)) {
//            try {
//                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//
//                for (FileItem item : multiparts) {
//                    if (!item.isFormField()) {
//                        String name = new File(item.getName()).getName();
//                        item.write(new File("../" + File.separator + name));
//                    }
//                }
//
//                //File uploaded successfully
//                request.setAttribute("message", "File Uploaded Successfully");
//            } catch (Exception ex) {
//                request.setAttribute("message", "File Upload Failed due to " + ex);
//            }
//
//        }

        String descricao = request.getParameter("txtDescricao").trim();
        String resenha = request.getParameter("txtResenha").trim();
        String strPreco = request.getParameter("txtPreco").replace(".", "").replace(",", ".").trim();
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
            erro = "Descrição deve ter 3 ou mais caracteres";
        } else if (!precoOk) {
            ok = false;
            erro = "Valor para o preço invalido";
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

        ProdutoDAO dao = new ProdutoDAO();
        dao.insert(produto);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
