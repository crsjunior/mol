
$(function()
{
    $('#txtDescricao').focus();

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
        pesquisar();
    });

    $('#btnCancelaAdicaoProduto').click(function() {
        cancelar();
    });

    $('#btnCadastrar').click(function() {

        var msg = "";
        var descricao = $.trim($('#txtDescricao').val());
        var estabelecimento = $.trim($('#estabelecimento :selected').val());
        var itens = $('#produtos_adicionados .cx-id').length;
        
        if (descricao.length < 10) {
            msg += "A descrição da lista, deve contér pelo menos 10 caracteres.\n";
        }
        
        if(estabelecimento==0) {
            msg += "É necessário selecionar o estabelecimento.\n";
        }
        
        if(itens>0) {
            var precos = verificaPrecos();
            if(!precos) {
                msg += "Você deve atribuir preço aos itens adicioandos.\n";
            }       
        } else {
            msg += "A lista deve contér pelo menos 1 item.\n";
        }
        
        if(msg != "") {
            alert(msg);
            return false;
        }else{
            document.formCadastroLista.submit();
        }

    });

});

function verificaPrecos() {
    var retorno = true;
    $.each($('#produtos_adicionados .cx-preco'), function(k, v) {
        if ($(v).val() == "") {
            retorno = false;
            return false;
        }
    });
    return retorno;
}

function pesquisar() {

    var ids_tot = $('#produtos_adicionados .cx-id').length;
    var ids = "";
    var buscar = $('#buscar').val();

    $.each(
            $('#produtos_adicionados .cx-id'),
            function(k, v) {
                ids += $(v).val() + ",";
            }
    );

    ids = ids.substring(0, ids.length - 1);

    $.post('lista_produtos_lista.jsp', {ids: ids, buscar: buscar}, function(data) {
        $('#lista-prods').html(data);
        $('#buscar').focus().keyup(function(e) {
            if (e.keyCode == 13) {
                pesquisar();
            }
        });
        $('#btn-pesquisar').click(function() {
            pesquisar();
        });
    });

}

function cancelar() {
    $('#div-produtos').fadeOut('fast');
    $('#dv-cont-form').fadeIn('fast');
    $('#produtos_adicionados .dv-delete').unbind('click').show();
    $('#produtos_adicionados .dv-delete').click(function() {
        $(this).parent().parent().remove();
        if ($('#produtos_adicionados .prod-lista').length == 0) {
            $('#produtos_adicionados').hide();
        }
        setFocusPrecoProduto();
    });
    setFocusPrecoProduto();
    $('#produtos_adicionados .cx-preco').mask('000.000.000.000.000,00', {reverse: true});
}

function setFocusPrecoProduto() {
    $.each($('#produtos_adicionados .cx-preco'), function(k, v) {
        if ($(v).val() == "") {
            $(v).focus();
            return false;
        }
    });
}

