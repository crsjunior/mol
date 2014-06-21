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
        
        String ids = request.getParameter("ids");
        String buscar = request.getParameter("buscar");
        buscar = buscar==null?"":buscar;
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.getProdutosByName(buscar, ids);

%>
<p>
    <input type="text" name="buscar" id="buscar" value="<%=buscar%>" placeholder="Pesquisar" />
    <a href="#self" id="btn-pesquisar"><img src="img/img-pesquisar.png" /></a>
</p>
<% if(produtos!=null) { %>
    <% if(produtos.size()>0) { %>
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
                    <input type="hidden" name="id" class="cx-id" value="<%=produtos.get(i).getId().toString()%>" />
                </div>

                <div class="p-preco">
                    <input type="text" name="preco" class="cx-preco cx-hide" value="" placeholder="Preço" size="6" />
                </div>

            </div>
            <div class="divisor">&nbsp;</div>
        </a>
        <% } %>
    <% } else { %>
        <p class="centralizar">Nenhum produto encontrado.</p>
    <% } %>
    
    <script language="javascript">
        $(function(){
           $('.produto').click(function(){
               var id = 'prod_'+Math.ceil(Math.random()*100);
               var nObj = $('#produtos_adicionados').append('<div id="'+id+'" class="prod-lista">'+$(this).html()+'</div>').show();
               $('#'+id+' .cx-preco').show().after('<div class="dv-delete"><a href="#self"><img src="img/icon-delete.png" /></a></div>');
               $('#'+id+' .cx-preco').removeClass('cx-hide');
               $(this).remove();
               if($('.produto').length==0) {
                   cancelar();
               }
           });
        });
    </script>
    
<% } else { %>
    <p class="centralizar">Nenhum produto encontrado.</p>
<% } %>


