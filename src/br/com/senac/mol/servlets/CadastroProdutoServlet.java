package br.com.senac.mol.servlets;

import br.com.senac.mol.entidades.Produto;
import br.com.senac.mol.entidades.Usuario;
import br.com.senac.mol.models.MensagensSessao;
import br.com.senac.mol.persistencia.ProdutoDAO;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/CadastroProdutoServlet")
public class CadastroProdutoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1000 * 1024;
    private int maxMemSize = 1000 * 1024;
    private File file;

    public CadastroProdutoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void init() {
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("arquuivo");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean ok = true;

        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:/sss"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    out.println("Uploaded Filename: " + fileName + "<br>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        /*
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
        */
    }
}
