$(function()
{
	/*
	$('#txtDescricao').focus();

	$('#btnCadastrar').click(function() {
		document.formCadastroProduto.submit();
	});
	*/

	$('#txtPreco').mask('000.000.000.000.000,00', { reverse : true });

	$('#btn-add-image').click(function() {
		$('#arqImagem').click();
	});
});
