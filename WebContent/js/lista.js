
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
        cancelar();       
    });

});

function cancelar() {
    $('#div-produtos').fadeOut('fast');
    $('#dv-cont-form').fadeIn('fast');
    $('.dv-delete').unbind('click');
    $('.dv-delete').click(function(){
       alert($(this).parent().html()); 
    });
}

