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

    // mensagens:
    if (mensagens != null) {
        // algum erro?
        if (mensagens.get("erro") != null) {
            status = mensagens.pop("erro");
            statusClass = "vermelho";            
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>MOL</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
        <script type="text/javascript">
            $(function()
            {
                $('#txtNome').focus();
                $('#btnCadastrar').click(function()
                {
                    var msg = "";
                    var nome = $.trim($('#txtNome').val());
                    var endereco = $.trim($('#txtEndereco').val());
                    var numero = $.trim($('#txtNumero').val());
                    var bairro = $.trim($('#txtBairro').val());
                    var cidade = $.trim($('#txtCidade').val());
                    var uf = $.trim($('#txtUF').val());
                    
                    if(nome=="") {
                        msg += "O campo nome é obrigatório.\n";
                    }
                    if(endereco=="") {
                        msg += "O campo endereço é obrigatório.\n";
                    }
                    if(bairro=="") {
                        msg += "O campo bairro é obrigatório.\n";
                    }
                    if(cidade=="") {
                        msg += "O campo cidade é obrigatório.\n";
                    }
                    if(uf.length!=2) {
                        msg += "O campo UF deve ser informado.\n";
                    }
                    
                    if(msg!="") {
                        alert(msg);
                        return false;
                    } else {
                        $('#formCadastroEstabelecimento').submit();
                    }
                });
            });
        </script>
    </head>

    <body>

        <form id="formCadastroEstabelecimento" name="formCadastroEstabelecimento" action="CadastroEstabelecimentoServlet" method="post">
            <h1><strong>M</strong>OL</h1>
            <br />
            <h2>Cadastro de Estabelecimento</h2>
            <p>
                <input id="txtNome" name="txtNome" type="text" value="" placeholder="*Descrição" />
            </p>
            <p>
                <input id="txtEndereco" style="width:194px !important;" name="txtEndereco" type="text" value="" placeholder="*Endereço" />
                <input id="txtNumero" style="width:30px !important;" name="txtNumero" type="text" value="" placeholder="nº" />
            </p>
            <p>
                <input id="txtBairro" name="txtBairro" type="text" value="" placeholder="*Bairro" />
            </p>
            <p>
                <input id="txtCidade" style="width:194px !important;" name="txtCidade" type="text" value="" placeholder="*Cidade" />
                <input id="txtUF" style="width:30px !important;" name="txtUF" type="text" value="" placeholder="*UF" />
            </p>

            <p class="controles">
                <a id="btnCadastrar" class="btn btn-primary btn_tela_inicial">Cadastrar Estabelecimento</a>
                <a href="<%=caminho%>/index.jsp" class="btn btn_tela_inicial">Cancelar</a>
            </p>
        </form>

    </body>
</html>