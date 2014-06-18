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
    
    .prod-lista {
        display:block;
        padding:6px 0;
        margin:0 auto;
        margin-bottom:2px;
        width:100%;
        color:#C6C6C6;
        border:1px solid #262626;
        clear:both;
        float:none;
        background: #1e1e1e; /* Old browsers */
        background: -moz-linear-gradient(top,  #1e1e1e 0%, #2b2b2b 100%); /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#1e1e1e), color-stop(100%,#2b2b2b)); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top,  #1e1e1e 0%,#2b2b2b 100%); /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top,  #1e1e1e 0%,#2b2b2b 100%); /* Opera 11.10+ */
        background: -ms-linear-gradient(top,  #1e1e1e 0%,#2b2b2b 100%); /* IE10+ */
        background: linear-gradient(to bottom,  #1e1e1e 0%,#2b2b2b 100%); /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1e1e1e', endColorstr='#2b2b2b',GradientType=0 ); /* IE6-9 */
    }
    
    .prod-lista:hover {
        color:#fff;
        background: #1b3d87; /* Old browsers */
        background: -moz-linear-gradient(top,  #1b3d87 0%, #2989d8 50%, #207cca 51%, #409de5 100%); /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#1b3d87), color-stop(50%,#2989d8), color-stop(51%,#207cca), color-stop(100%,#409de5)); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top,  #1b3d87 0%,#2989d8 50%,#207cca 51%,#409de5 100%); /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top,  #1b3d87 0%,#2989d8 50%,#207cca 51%,#409de5 100%); /* Opera 11.10+ */
        background: -ms-linear-gradient(top,  #1b3d87 0%,#2989d8 50%,#207cca 51%,#409de5 100%); /* IE10+ */
        background: linear-gradient(to bottom,  #1b3d87 0%,#2989d8 50%,#207cca 51%,#409de5 100%); /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1b3d87', endColorstr='#409de5',GradientType=0 ); /* IE6-9 */
    }
    
    .prod-lista:after {
        content: " ";
        display: block; 
        height: 0; 
        clear: both;
    }
    
    .prod-img {
        float:left;
        line-height: 100%;
        height:100%;
        padding:2px 4px;
    }
    
    .prod-desc p {
        line-height: 10px;
    }
    
    .prod-desc {
        padding:4px 10px 0 10px;
        float:left;
        
    }
    
</style>
<% if(produtos!=null) { %>
    <% for(int i=0;i<produtos.size();i++){ %>
    <a href="#self" class="prod-lista">
        <div class="prod-img">
            <% if(produtos.get(i).getImagem()!=null) { %>
                <%
                File file = new File(application.getRealPath("")+"/img/"+produtos.get(i).getImagem().get(0).getArquivo());
                if(file.exists()) {
                %>
                <img width="40" alt="Imagem indisponível" src="<%=caminho%>/img/<%=produtos.get(i).getImagem().get(0).getArquivo()%>" />
                <% } else { %>
                <img width="40" alt="Imagem indisponível" src="<%=caminho%>/img/sem_imagem.png" />
                <% } %>
            <% } else { %>
            <img width="40" src="<%=caminho%>/img/sem_imagem.png" />
            <% } %>
        </div>
        <div class="prod-desc">
            <p><%=produtos.get(i).getDescricao()%></p>
            <p><%=Uteis.formataCurrency(produtos.get(i).getPreco())%></p>
        </div>
    </a>
    <% } %>
<% } else { %>

<% } %>


