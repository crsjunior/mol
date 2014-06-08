$(function() {
	
	$('#usuario').focus();
	$('#btnLogin').click(function(){
		login2();
	});
	
	$('#senha').keyup(function(e){
		if(e.keyCode==13){
			login();
		}
	});
	
});

function login2()
{
	document.formLogin.submit();
}

function login()
{
	//alert('porra');
	var usuario = $.trim( $('#usuario').val() );
	var senha = $.trim( $('#senha').val() );
	if(usuario==""||senha==""){
		alert('É necessário informar o usuário e senha para prosseguir');
	} else {
		var msg = "";
		$.getJSON('http://localhost:8080/webmol/login',{usuario:usuario,senha:senha},function(retorno){
			if(retorno.erro!=undefined){
				for(i=0;i<retorno.mensagem.length;i++){
					msg += retorno.mensagem[i].mensagem+"\n";
				}
				if(retorno.erro==false) {
					window.location.href = "menu.jsp";
				} else {
					alert('Erro\n\n'+msg);
				}
			}
		});
	}
}