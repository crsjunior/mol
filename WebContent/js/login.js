$(function()
{
	$('#txtEmail').focus();

	$('#btnLogin').click(function() {
		login();
	});

	$('#txtSenha').keyup(function(e) {
		if (e.keyCode == 13) {
			login();
		}
	});
});

function login()
{
	document.formLogin.submit();
}
