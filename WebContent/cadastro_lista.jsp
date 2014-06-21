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
                clear:both;
                padding:6px;
                background:#E3E3E3;
            }

            #produtos_adicionados {
                width:368px;
                padding:4px;
                display:none;
                background:#E3E3E3;
            }
            
            #div-produtos  {
                top:0;
                width:368px;
                z-index:200;
                display:none;
                height:100%;
                bottom:0;
                position:absolute;
                padding-top:75px;
            }
            
            .detalhe {
                width:100%;
                padding:2px;
                background:#E3E3E3;
            }
            
            #formCadastroLista {
                z-index:-1;
            }

            #btnCancelaAdicaoProduto {
                width:200px;
                display:block !important;
                margin:20px auto;
            }
            #div-lista-prod {
                margin:0 auto;
            }
            
            .lista-interna {
                height:100%;
                float:none;
                clear:both;
                padding:6px 0;
            }

            .prod-lista {
                display:block;
                margin:0 auto;
                width:100%;
                color:#2E2828;
                clear:both;                
                float:none;
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
                text-align:left;
            }

            .prod-desc {
                padding:4px 10px 0 10px;
                float:left;

            }
            
            .divisor {
                height:1px;
                background:#F2F2F2;
                border-bottom:1px solid #BFBFBF;
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