$(function() {
	
	$('#nome').focus();
	
	$('#btnCadastrar').click(function() {
		document.formCadastroUsuario.submit();
	});
	
	$('#cadastrar').click(function(){
		var nome = $('#nome').val();
		var email = $('#email').val();
		var senha = $('#senha').val();
		var confirmaSenha = $('#confirmaSenha').val();		
		
		var msg = "";
		$.getJSON('http://localhost:8080/webmol/usuario',{
			nome:nome,
			email:email,
			senha:senha,
			confirmaSenha:confirmaSenha
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