
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
        pesquisar();
    });

    $('#btnCancelaAdicaoProduto').click(function() {
        cancelar();
    });
    
    $('#btnSubmit').click(function(){
       var descricao = $('#txtDescricao').val();
       var estabelecimento = $('#estabelecimento :selected').val();
       var itens = $('.cx-id').length;
       
        if (itens > 0) {
            var verificaPrecos = verificaPrecos();

        }
       
    });

});

function verificaPrecos() {
    $.each($('.cx-preco'),function(k,v) {
       if($(v).val()=="") { 
           return false;
       }
    });
    return true;
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
    
    $.post('lista_produtos_lista.jsp', {ids: ids, buscar:buscar}, function(data) {
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
    $('.dv-delete').unbind('click');
    $('.dv-delete').click(function() {
        $(this).parent().parent().parent().remove();
        if ($('#produtos_adicionados .prod-lista').length == 0) {
            $('#produtos_adicionados').hide();
        }
        setFocusPrecoProduto();
    });
    setFocusPrecoProduto();
}

function setFocusPrecoProduto() {
    $.each($('#produtos_adicionados .cx-preco'), function(k, v) {
        if ($(v).val() == "") {
            $(v).focus();
            exit;
        }
    });
}

