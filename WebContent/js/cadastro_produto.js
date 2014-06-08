$(function()
{
	$('#descricao').focus();
	
	$("#cadastrar_produto").click(function()
	{
		var descricao = $("#descricao").val();
		var resenha = $("#resenha").val();
		var preco = $("#prodpreco").val();
		
		var msg = "";
		$.getJSON('http://localhost:8080/webmol/cadastro_produto', {
			descricao:descricao,
			resenha:resenha,
			preco:preco
		}, function(retorno) {
			if (retorno.erro != undefined) {
				for (i = 0; i < retorno.mensagem.length; i++) {
					msg += retorno.mensagem[i].mensagem + "\n";
				}
				if (retorno.erro == false) {
					window.location.href = "menu.html";
				} else {
					alert('Erro [' + retorno.cod + ']\n' + msg);
				}
			}
		});
		alert(descricao + '\n' + resenha + '\n' + preco);
	});
});
