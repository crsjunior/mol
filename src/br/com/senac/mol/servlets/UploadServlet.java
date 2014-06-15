package br.com.senac.mol.servlets;

import br.com.senac.mol.entidades.Imagem;
import br.com.senac.mol.models.RetornoUploadArquivo;
import br.com.senac.mol.persistencia.ImagemDAO;
import br.com.senac.mol.utils.Geradores;
import br.com.senac.mol.utils.Uteis;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    int BUFFER_LENGTH = 4096;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String hash = request.getParameter("hash").trim();
        PrintWriter out = response.getWriter();
        String webAppPath = getServletContext().getRealPath("/");
        List<RetornoUploadArquivo> arquivosBaixados = new ArrayList<RetornoUploadArquivo>();

        for (Part part : request.getParts()) {
            InputStream is = request.getPart(part.getName()).getInputStream();
            String fileName = getFileName(part);
            if(fileName!=null) {
                String extensao = Uteis.getExtensao(fileName);
                Geradores g = new Geradores();
                String randomNameFile = g.getHash() + "." + extensao;
                String caminhoArquivoNovo = webAppPath + "//img//";
                if (extensao != null) {
                    FileOutputStream os = new FileOutputStream(caminhoArquivoNovo+randomNameFile);
                    byte[] bytes = new byte[BUFFER_LENGTH];
                    int read = 0;
                    while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
                        os.write(bytes, 0, read);
                    }

                    RetornoUploadArquivo arquivoBaixado = new RetornoUploadArquivo();
                    arquivoBaixado.setNomeArquivo(randomNameFile);
                    arquivoBaixado.setDiretorio(caminhoArquivoNovo);
                    arquivoBaixado.setStatus(true);
                    arquivosBaixados.add(arquivoBaixado);

                    os.flush();
                    os.close();
                } else {

                    RetornoUploadArquivo arquivoBaixado = new RetornoUploadArquivo();
                    arquivoBaixado.setNomeArquivo(randomNameFile);
                    arquivoBaixado.setDiretorio(caminhoArquivoNovo);
                    arquivoBaixado.setStatus(false);
                    arquivosBaixados.add(arquivoBaixado);

                }
                is.close();
            }
            
        }
        
        Gson retorno = new Gson();
        out.println(retorno.toJson(arquivosBaixados));        
        
    }

    private String getFileName(Part part) {
        if(part.getContentType()!=null) {
            return part.getSubmittedFileName();
        }
        return null;
    }

}
