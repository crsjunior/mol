<%@page import="br.com.senac.mol.utils.Uteis"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="br.com.senac.mol.entidades.Produto"%>
<%@page import="br.com.senac.mol.persistencia.ProdutoDAO"%>
<%@page import="br.com.senac.mol.models.MensagensSessao"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>

<%
	// variaveis:
	HttpSession sessao = request.getSession();
	MensagensSessao mensagens = (MensagensSessao) sessao.getAttribute("mensagens");
	String caminho = request.getContextPath();
        
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.getProdutosByName(null);
        
        

%>
<style type="text/css">
    

</style>
<% if(produtos!=null) { %>
    <% for(int i=0;i<produtos.size();i++){ %>
    <a href="#self" class="prod-lista produto">
        <div class="lista-interna">
            <div class="prod-img">
                <% if(produtos.get(i).getImagem()!=null) { %>
                    <%
                    File file = new File(application.getRealPath("")+"/img/"+produtos.get(i).getImagem().get(0).getArquivo());
                    if(file.exists()) {
                    %>
                    <img class="img-prod" width="40" alt="Imagem indisponível" src="<%=caminho%>/img/<%=produtos.get(i).getImagem().get(0).getArquivo()%>" />
                    <% } else { %>
                    <img class="img-prod" width="40" alt="Imagem indisponível" src="<%=caminho%>/img/sem_imagem.png" />
                    <% } %>
                <% } else { %>
                <img width="40" src="<%=caminho%>/img/sem_imagem.png" />
                <% } %>
            </div>
            <div class="prod-desc">
                <p><%=produtos.get(i).getDescricao()%></p>
                <p><%=Uteis.formataData(produtos.get(i).getDataCadastro())%></p>
                <input type="hidden" name="id" value="<%=produtos.get(i).getId().toString()%>" />
            </div>
            <div class="p-preco"><%

            String preco = Uteis.formataCurrency(produtos.get(i).getPreco());
            String centavos = preco.substring(preco.indexOf(","));
            preco = preco.replace(centavos,"");
            out.print("<div class=\"sp-preco\">"+preco+"</div><div class=\"sp-centavos\">"+centavos+"</div>");

            %>

            </div>
        </div>
        <div class="divisor">&nbsp;</div>
    </a>
    
    
    <% } %>
    
    
    <script language="javascript">
        $(function(){
           $('.produto').click(function(){
               $('#produtos_adicionados').append( '<div class="prod-lista">'+$(this).html()+'</div>' ).show();
               //$(this).next().remove();
               $(this).remove();
           });
        });
    </script>
    
<% } else { %>

<% } %>


