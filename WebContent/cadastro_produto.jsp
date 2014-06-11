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
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>MOL</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jMask.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/cadastro_produto.js"></script>
    </head>

    <body>

        <form id="formCadastroProduto" name="formCadastroProduto" action="CadastroProdutoServlet" method="post">
            <h1><strong>M</strong>OL</h1>
            <br />
            <h2>Cadastro de Produto</h2>
            <p>
                <input id="txtDescricao" name="txtDescricao" type="text" value="<%=descricao%>" placeholder="Descrição" />
                <textarea id="txtResenha" name="txtResenha" rows="4" placeholder="Resenha"><%=resenha%></textarea>
                <input id="txtPreco" name="txtPreco" type="text" value="<%=preco%>" placeholder="Preço">
                <a class="btn btn-primary btn_tela_inicial" id="btn-add-image">Adicionar Imagem</a>
            </p>
            <div id="status" class="<%=statusClass%>"><%=status%></div>
            <p>
                <a id="btnCadastrar" class="btn btn-primary btn_tela_inicial">Salvar</a>
                <a href="<%=caminho%>/index.jsp" class="btn btn_tela_inicial">Cancelar</a>
            </p>
            <div class="div-arq">
                <input type="file" name="arquuivo" id="arquivo" accept="image/*" />
            </div>
        </form>

    </body>
</html>