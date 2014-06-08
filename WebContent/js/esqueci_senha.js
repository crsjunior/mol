$(function() {
	
	$('#email').focus();
	
	
	$('#recuperar').click(function(){
		esqueceuSenha();
	});
	
	$('#email').keyup(function(e){
		if(e.keyCode==13) {
			esqueceuSenha();
			e.preventDefault();
		}
	});
	
});

function esqueceuSenha(){

	var email = $('#email').val();
	
	var msg = "";
	$.getJSON('http://localhost:8080/webmol/esqueci_senha',{
		email:email
	},function(retorno){
		if(retorno.erro!=undefined){
			for(i=0;i<retorno.mensagem.length;i++){
				msg += retorno.mensagem[i].mensagem+"\n";
			}
			if(retorno.erro==true){
				alert('Erro ['+retorno.cod+']\n'+msg);
			} else {
				alert(msg);
			}
		}
	});
}