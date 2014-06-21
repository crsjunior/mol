<%@page import="br.com.senac.mol.entidades.Estabelecimento"%>
<%@page import="java.util.List"%>
<%@page import="br.com.senac.mol.persistencia.EstabelecimentoDAO"%>
<%@page import="br.com.senac.mol.models.MensagensSessao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>

<%
    // variaveis:
    HttpSession sessao = request.getSession();
    MensagensSessao mensagens = (MensagensSessao) sessao.getAttribute("mensagens");
    String caminho = request.getContextPath();
    String status = "&nbsp;";
    String statusClass = "";
    String descricao = "";
    String resenha = "";
    String preco = "";

    // mensagens:
    if (mensagens != null) {
        // algum erro?
        if (mensagens.get("erro") != null) {
            status = mensagens.pop("erro");
            statusClass = "vermelho";
            descricao = mensagens.pop("descricao");
            resenha = mensagens.pop("resenha");
            preco = mensagens.pop("preco");
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>MOL</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lista.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/lista.js"></script>
    </head>

    <body>

        <form id="formCadastroLista" name="formCadastroLista" action="CadastroListaServlet" method="post">
            <h1><strong>M</strong>OL</h1>
            <br />
            <h2>Cadastro de Lista</h2>
            <div id="dv-cont-form">
                <p>
                    <input id="txtDescricao" name="txtDescricao" type="text" value="<%=descricao%>" placeholder="Descrição" />
                </p>
                <p>
                    <select name="estabelecimento" id="estabelecimento">
                        <option value="0">Estabelecimento</option>
                        <%
                        
                        EstabelecimentoDAO estab = new EstabelecimentoDAO();
                        List<Estabelecimento> estabelecimentos = estab.getEstabelecimentos();
                        
                        if(estabelecimentos==null) {
                            
                        } else {
                            for(Estabelecimento estabs : estabelecimentos) {
                                %><option value="<%=estabs.getId()%>"><%=estabs.getNome()%></option><%
                            }
                        }
                        %>
                    </select>
                </p>

                <div id="status" class="<%=statusClass%>"><%=status%></div>
                <div id="produtos_adicionados">
                    
                </div>
                <p>
                    <a id="btnAdicionarProdutos" class="btn btn-primary btn_tela_inicial">Adicionar produtos</a>
                    <a id="btnCadastrar" class="btn btn-primary btn_tela_inicial">Cadastrar Lista</a>
                    <a href="<%=caminho%>/index.jsp" class="btn btn_tela_inicial">Cancelar</a>
                </p>
            </div>
        </form>
        <div id="div-produtos">
            <div class="detalhe">
                <div id="lista-prods"></div>
                <a href="#self" id="btnCancelaAdicaoProduto" class="btn btn_tela_inicial">Cancelar</a>
            </div>
        </div>
        <div id="overlay">&nbsp;</div>
    </body>
</html>