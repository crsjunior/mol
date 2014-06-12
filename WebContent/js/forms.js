$(function() {

    /*
     $.post('http://localhost:8088/mol/teste.php',{usuario:"lucas",senha:"senha"},
     function(data){
     alert(data);
     }
     );
     */
	 /*
	var dt = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.app.senac.com.br/"><soapenv:Header/><soapenv:Body><ws:login><usuario>usuario</usuario><senha>senha</senha></ws:login></soapenv:Body></soapenv:Envelope>';
	$.ajax({
	   type: "GET",
	   url: "http://localhost:8080/webmol/mol",
	   data: {dt},
	   contentType: "application/json; charset=utf-8",
	   dataType: "json",
	   success: function(msg, status) {
		   alert("ok: "+msg.d);
	   },
	   error: function(xhr, msg, e) {
		   alert("error: "+e);
	   }
	});	 
	 */
	/*
    $.post('http://localhost:8080/mol/login', {usuario:"lucas",senha:"senha"}).done(function(data) {
        alert(data);
    });
	*/

    $('input:first').focus();

    /*
     $.post('http://localhost:8080/MOL/MOL/login',{usuario:"lucas",senha:"senha"},function(data){
     alert(data);
     });
     */

});