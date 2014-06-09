$(function()
{
	$('#txtDescricao').focus();

	$('#btnCadastrar').click(function() {
		document.formCadastroLista.submit();
	});
});