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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
        <script type="text/javascript">
            $(function()
            {
                $('#txtDescricao').focus();
                $('#btnCadastrar').click(function()
                {
                    $('#formCadastroLista').submit();
                });

                $('#btnAdicionarProdutos').click(function() {
                    $('#dv-cont-form').fadeOut('fast');
                    //$('#overlay').show('fast');
                    $('#div-produtos').css(
                            {
                                left: ($('body').width() / 2) - $('#div-produtos').width() / 2,
                                height: $('#formCadastroLista').height()
                            }
                    ).fadeIn('fast');
                    $("#lista-prods").html('<p align="center"><img src="/mol/img/icon_loading.gif" /></p>');
                    $.post('lista_produtos_lista.jsp', {}, function(data) {
                        $('#lista-prods').html(data);
                    });
                });

                $('#btnCancelaAdicaoProduto').click(function() {
                    //$('#overlay').fadeOut('fast');
                    $('#div-produtos').fadeOut('fast');
                    $('#dv-cont-form').fadeIn('fast');
                });

            });
        </script>
        <style type="text/css">

            #lista-prods {
                margin:30px auto;
                background:#F2F2F2;
                border:1px solid #D4D4D4;
                width:270px;
            }

            #div-produtos {
                top:0;
                width:368px;
                z-index:200;
                display:none;
                display:none;
                height:100%;
                bottom:0;
                position:absolute;
                padding-top:75px;
            }
            #formCadastroLista {
                z-index:-1;
            }

            #btnCancelaAdicaoProduto {
                width:200px;
                display:block !important;
                margin:0 auto;
            }
            #div-lista-prod {
                margin:0 auto;
            }
            
            .lista-interna {
                height:100%;
                float:none;
                clear:both;
                border-bottom:1px solid #595959;
                padding:6px 0;
            }

            .prod-lista {
                border-color:#1F2BAD;
                border-style:solid;
                border-width:0 0 0 3px;
                display:block;
                margin:0 auto;
                width:100%;
                color:#2E2828;
                clear:both;                
                float:none;
                background: #c6c6c6; /* Old browsers */
                background: -moz-linear-gradient(top,  #c6c6c6 0%, #919191 50%, #7f7f7f 51%, #c6c6c6 100%); /* FF3.6+ */
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#c6c6c6), color-stop(50%,#919191), color-stop(51%,#7f7f7f), color-stop(100%,#c6c6c6)); /* Chrome,Safari4+ */
                background: -webkit-linear-gradient(top,  #c6c6c6 0%,#919191 50%,#7f7f7f 51%,#c6c6c6 100%); /* Chrome10+,Safari5.1+ */
                background: -o-linear-gradient(top,  #c6c6c6 0%,#919191 50%,#7f7f7f 51%,#c6c6c6 100%); /* Opera 11.10+ */
                background: -ms-linear-gradient(top,  #c6c6c6 0%,#919191 50%,#7f7f7f 51%,#c6c6c6 100%); /* IE10+ */
                background: linear-gradient(to bottom,  #c6c6c6 0%,#919191 50%,#7f7f7f 51%,#c6c6c6 100%); /* W3C */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#c6c6c6', endColorstr='#c6c6c6',GradientType=0 ); /* IE6-9 */
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
            
            .lista-interna:after {
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

            .p-preco {
                margin-right:20px;
                text-align:right;
                float:right;
                width:65px;
            }

            .sp-preco {
                font-size:26px;
                display:inline-block;
            }

            .sp-centavos {
                position:absolute;
                font-size:10px;
                display:inline-block;
                margin-top:-5px;
            }
            
            #overlay {
                display:none;
                background:url('img/bg-lista.png') repeat-y;
                width:371px;
                z-index:100;
                height:100%;
                position:fixed;
                left:50%;
                margin-left:-186px;
                top:0;
            }

        </style>
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

                <div id="status" class="<%=statusClass%>"><%=status%></div>
                <p>
                    <a id="btnAdicionarProdutos" class="btn btn-primary btn_tela_inicial">Adicionar produtos</a>
                    <a id="btnCadastrar" class="btn btn-primary btn_tela_inicial">Cadastrar Lista</a>
                    <a href="<%=caminho%>/index.jsp" class="btn btn_tela_inicial">Cancelar</a>
                </p>
            </div>
        </form>
        <div id="div-produtos">
            <div id="lista-prods"></div>
            <a href="#self" id="btnCancelaAdicaoProduto" class="btn btn_tela_inicial">Cancelar</a>
        </div>
        <div id="overlay">&nbsp;</div>
    </body>
</html>