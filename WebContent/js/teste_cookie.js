$(function()
{
	$('#btn_teste_cookie').click(function() {
		//alert('ok');
		var msg = "";
		$.getJSON('http://localhost:8080/webmol/HelloForm',{
		},function(retorno){
			if(retorno.erro!=undefined){
				for(i=0;i<retorno.mensagem.length;i++){
					msg += retorno.mensagem[i].mensagem+"\n";
				}
				if(retorno.erro==false){
					window.location.href = "menu.html";
				} else {
					alert('Erro ['+retorno.cod+']\n'+msg);
				}
			}
		});
	});
	
});