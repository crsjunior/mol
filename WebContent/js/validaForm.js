/*
 * Script JavaScript que auxilia o preenchimento correto de um formulario
 * 
 *   Trabalhando em tempo real (tempo de digitacao), ele verifica e valida o conteudo dos
 *   campos de um formulario em toda a vez que ocorrer uma alteracao nos seus conteudos.
 * 
 *   Tipos de campos suportados (tags):
 *     INPUT type="text", INPUT type="password", TEXTAREA, SELECT
 * 
 *   Atributos disponiveis: (Legenda: 1=somente INPUT | 2=somente SELECT | 3=todos):
 *     data-obrigatorio="boolean"   --> (3) sinaliza um campo como sendo de preenchimento obrigatorio, ou nao.
 *     data-tammin="n"              --> (1) tamanho minimo de caracteres do campo.
 *     data-tammax="n"              --> (1) tamanho maximo de caracteres do campo.
 *     data-selectexcluir="boolean" --> (2) sinaliza se o primeiro item do SELECT (cabecalho) devera ser excluido
 *                                          apos outro item ser selecionado.
 *     data-tipo="tipo"             --> (1) o tipo de conteudo desejado no campo, que pode ser:
 *                                          "int"   -> somente permite numeros inteiros.
 *                                          "float" -> somente permite numeros de ponto flutuante.
 *                                          "date"  -> somente permite datas.
 *                                          "email" -> somente permite enderecos de email.
 *                                          "fone"  -> somente permite numeros de telefone.
 * 
 *   Instrucoes gerais de uso (da construcao do formulario no HTML):
 *     Eh necessario um trecho de script no inicio do HTML, que declarara as seguintes variaveis:
 *       - formNome="valor"              --> Onde o valor eh o id do formulario, no qual sera efetuado o submit.
 *       - formCampos={ campo: boolean } --> Objeto (Array) dos id's dos campos do formulario que serao
 *                                           submetidos a validacao, seguidos do seu status de validade inicial.
 *       - formSubmitOnEnter="boolean"   --> Indica se o formulario sera submetido ao ser pressionada a tecla Enter
 *                                           quando o foco estiver sobre um dos campos (opcional).
 *     Sobre o botao (ou link) Submit: O botao (ou link) para submeter o formulario devera ter seu id="btnSubmit".
 * 
 * Autor: Carlos R. Schwarz Júnior
 * E-mail: schwarz.junior@gmail.com
 * Data: 10/09/2013
 */

// mantem as classes css aplicadas atualmente nos campos do formulario:
var formCamposClass = {};
// id do botao de submit:
var btnSubmitClass = '';
// status da validacao do formulario, true quando ok, caso contrario false:
var formOk = false;

/**
 * Inicializando o formulário.
 */
$(document).ready(function()
{
	if (typeof formCores !== 'undefined') {
		for (var key in formCores) {
			switch (key) {
			case 'corFundo':
				corFundo(formCores[key]);
				break;
			case 'corBordaFieldset':
				corBordaFieldset(formCores[key]);
				break;
			case 'corLegend':
				corLegend(formCores[key].split(' ')[0], formCores[key].split(' ')[1]);
				break;
			}
		}
	}

	// recuperando a classe CSS atual do botao (ou link) de submit do formulario:
	btnSubmitClass = $('#btnSubmit').prop('class');

	// vinculando evento ao botao (ou link) de submit do formulario:
	$('#btnSubmit').click(function() {
		efetuarSubmit();
	});

	// verificando se o formulario sera submetido ao se pressionar a tecla Enter quando em cima de um dos campos:
	var submitPorCampo = (typeof formSubmitOnEnter !== 'undefined' && formSubmitOnEnter == true);

	if (typeof formCampos !== 'undefined') {
		// percorrendo os campos do formulario:
		for (var key in formCampos) {
			if ($('#' + key).existe()) {
				// salvando as classes CSS atuais do campo do formulario:
				formCamposClass[key] = $('#' + key).prop('class');
				// vinculando eventos ao campo do formulario:
				$('#' + key).on('change keypress paste input', function(event) {
					validarCampoFormulario(this.id, event);
				});
				if (submitPorCampo) {
					$('#' + key).on('keydown', function(event) {
						if (event.keyCode == 13) {
							efetuarSubmit();
						}
					});
				}
				// efetuando a validacao inicial do campo do formulario:
				validarCampoFormulario(key);
			} else {
				alert('O campo de id="' + key + '" não foi encontrado no formulário,' + '\nportanto, será desconsiderado do processo de validação!');
				formCampos[key] = true;
			}
		}

		// dando o foco para o primeiro campo do formulario:
		if ($('#' + Object.keys(formCampos)[0]).existe()) {
			$("#" + Object.keys(formCampos)[0]).focus();
			setPosicaoCursor(Object.keys(formCampos)[0], 0);
		}
	}
});

/**
 * Efetua o submit no formulario, mas somente se este estiver valido.
 */
function efetuarSubmit()
{
	if (formOk) {
		$('#' + formNome).submit();
	}
}

/**
 * Seta a posição do cursor em um elemento de formulário INPUT de tipo text.
 * @param id String com o id do elemento de formulário INPUT de tipo text
 * @param posCursor Integer com a posição onde será colocado o cursor
 */
function setPosicaoCursor(id, posCursor)
{
	var ele = document.getElementById(id);
	if (ele != null) {
		if (ele.createTextRange) {
			// Internet Explorer
			var range = ele.createTextRange();
			range.move("character", posCursor);
			range.select();
		} else {
			if (ele.selectionStart || ele.selectionStart == 0) {
				// Google Chrome: ele.selectionStart, Mozilla Firefox: ele.selectionStart == 0
				ele.focus();
				ele.setSelectionRange(posCursor, posCursor);
			} else {
				ele.focus();
			}
		}
	}
	ele.select();
}


/**
 * Verifica a validade de um campo e de todo o formulário.
 */
function validarCampoFormulario(id, e)
{
	var ele = document.getElementById(id);
	// atributo: preenchimento obrigatório.
	var attr = ele.getAttribute("data-obrigatorio");
	var obrigatorio = (attr != null && attr == "true") ? true : false;
	// atributo: tamanho mínimo do texto.
	var attr = ele.getAttribute("data-tammin");
	var tammin = attr != null ? attr : 0;
	// atributo: excluir primeiro item de um SELECT ao selecionar.
	var attr = ele.getAttribute("data-selectexcluir");
	var selectexcluir = (attr != null && attr == "true") ? true : false;
	// tamanho do texto do campo.
	var tamanho = ele.value.length;

	if (e != null && ele.tagName == "SELECT" && e.type == "change" && ele.selectedIndex != 0 && selectexcluir == true) {
		ele.remove(0);
	}

	// validando o campo.
	if (tamanho == 0) {
		formCampos[id] = !obrigatorio;
	} else {
		formCampos[id] = (tamanho < tammin || !checkTipo(id)) ? false : true;
	}

	if (formCampos[id]) {
		ele.className = formCamposClass[id] + ' valido';
	} else {
		ele.className = formCamposClass[id] + ' invalido';
	}

	// verificando as validades dos campos.
	// se um único campo não estiver válido, o botão "btnSubmit" será desabilitado.
	var submitButtonDisabled = false;
	for (var key in formCampos) {
		if (formCampos[key] === false) {
			submitButtonDisabled = true;
			break;
		}
	}

	// aplicando classe CSS no botao "btnSubmit", conforme a validade do formulario:
	document.getElementById("btnSubmit").className = btnSubmitClass + (submitButtonDisabled ? ' disabled' : '');

	// setando variavel global com o status de validacao do formulario:
	formOk = !submitButtonDisabled;
}

/**
 * Verifica se o valor do campo corresponde ao tipo de dado correto.
 * @param String id Id do elemento para checar seu tipo
 * @returns Boolean True se o valor do elemento corresponder, caso contrário, False
 */
function checkTipo(id)
{
	var ele = document.getElementById(id);
	// atributo: tipo de campo.
	var tipo = ele.getAttribute("data-tipo");
	if (tipo == null) {
		return true;
	}
	try {
		switch (tipo) {
		case "int": { return isInt(ele.value); break; }
		case "float": { return isFloat(ele.value); break; }
		case "dinheiro": { return isDinheiro(ele.value); break; }
		case "date": { return isDate(ele.value); break; }
		case "email": { return isEmail(ele.value); break; }
		case "fone": { return isPhoneNumber_international(ele.value); break; }
		default: { return true; }
		}
	} catch (err) {
		alert(err);
		return false;
	}
}

function ValidaTelefone(tel)
{
	// (xx) xxxx-xxxx, (xx) xxxx-xxxxx.
	exp1 = /\(\d{2}\)\ \d{4}\-\d{4}/;
	// xx xxxx-xxxx, xx xxxx-xxxxx.
	exp2 = /\d{2}\ \d{4}\-\d{4}/;
	exp3 = /\d{2}\ \d{4}\d{4}/;
	if (exp1.test(tel.value) || exp2.test(tel.value) || exp3.test(tel.value)) {
		tel.className = "valido";
	} else {
		tel.className = "invalido";
	}
}


function isPhoneNumber(obj)
{
	//var ele = document.getElementById(obj);
	var valor = obj.value;
	var teste = valor.replace(/D/g, "");
	teste = teste.replace(/^(d{2})(d)/g, "($1) $2");
	teste = teste.replace(/(d)(d{4})$/, "$1-$2");
	//	document.getElementById("fone_cel").value = teste;
	obj.value = teste;
	alert(teste);
	//	return teste;
}

function mascara(o, f)
{
	v_obj = o;
	v_fun = f;
	setTimeout("execmascara()", 1);
}

function execmascara()
{
	v_obj.value = v_fun(v_obj.value);
}

function mtel(v)
{
	alert(v);
	// Remove tudo o que não é dígito.
	v = v.replace(/D/g, "");
	// Coloca parênteses em volta dos dois primeiros dígitos.
	v = v.replace(/^(d{2})(d)/g, "($1) $2");
	// Coloca hífen entre o quarto e o quinto dígitos.
	v = v.replace(/(d)(d{4})$/, "$1-$2");
	return v;
}


var isPhoneNumber_international = function(valor)
{
	// valida números de telefone nos formatos (com ou sem o sinal de +):
	// +xx-xxxx-xxxx, +xx.xxxx.xxxx, +xx xxxx xxxx.
	var fone_regexp = /^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/;
	return (valor.match(fone_regexp));
};

/**
 * Verifica se um valor corresponde a um endereço de e-mail.
 * @param String valor Valor para verificar se corresponde a um endereço de e-mail
 * @returns Boolean True se corresponde a um endereço de e-mail, caso contrário, False
 */
var isEmail = function(valor)
{
	var atpos = valor.indexOf("@");
	var dotpos = valor.lastIndexOf(".");
	return !(atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= valor.length);
};

/**
 * Verifica se um valor é um número inteiro.
 * @param String valor Valor para verificar se é um número inteiro
 * @returns Boolean True se for um número inteiro, caso contrário, False
 */
var isInt = function(valor)
{
	return (/^\-?([0-9]+)$/.test(valor));
};

/**
 * Verifica se um valor é um número com ponto decimal.
 * @param String valor Valor para verificar se é um número com ponto decimal
 * @returns Boolean True se for um número com ponto decimal, caso contrário, False
 */
var isFloat = function(valor)
{
	return (/^\-?([0-9]+(\.[0-9]+)?)$/.test(valor));
};

var isDinheiro = function(valor)
{
	return (/^\-?([0-9]+(\,[0-9][0-9])?)$/.test(valor));
};

/**
 * Valida uma data
 * @param string data Uma string representando uma data
 * @param string formato O formato em que a data se encontra [default = (d/m/Y)]
 * @returns Boolean True se a data for válido, caso contrário False
 */
function isDate(data, formato)
{
	var dia;
	var mes;
	var ano;
	if (!formato) {
		dia = 1;
		mes = 2;
		ano = 3;
	} else {
		// será usado o formato passado.
		formato = formato.toLowerCase().replace(/[^dmy]/g, "");
		dia = formato.indexOf("d") + 1;
		mes = formato.indexOf("m") + 1;
		ano = formato.indexOf("y") + 1;
	}

	//var matches = data.match(/^\b(\d+)\D(\d+)\D(\d+)\b\b(?:\s+(\d{1,2})\D(\d{2})\D*((\d{2})?))?\b$/);
	//var matches = data.match(/^\b(\d+)[-/.](\d+)[-/.](\d+)\b\b(?:\s+(\d{1,2}):(\d{2})\D*((\d{2})?))?\b$/);
	var matches = data.match(/^\b(\d+)[-\/.](\d+)[-\/.](\d+)\b\b(?:\s+(\d{1,2}):(\d{2})\D*((\d{2})?))?\b$/);

	// validando a data.
	if (matches === null) {
		return false;
	}
	try {
		if (!validaData(matches[dia], matches[mes], matches[ano])) {
			return false;
		} else if (matches[4] !== undefined) {
			return false;
		} else {
			return true;
		}
	} catch (err) {
		return false;
	}
}

/**
 * Valida uma data
 * @param string dia Dia
 * @param string mes Mês
 * @param string ano Ano
 * @returns Boolean True se a data for válida, caso contrário False
 */
function validaData(dia, mes, ano)
{
	//var dateRegExp = /^(19|20)\d\d-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;
	var dateRegExp = /^[0-2][0-9]\d\d-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;

	if (!dateRegExp.test(ano + "-" + mes + "-" + dia)) {
		// formato inválido.
		return false;
	}
	if (dia == 31 && (/^0?[469]$/.test(mes) || mes == 11)) {
		// dia 31 em um mês de 30 dias.
		return false;
	} else if (dia >= 30 && mes == 2) {
		// mais de 29 dias em fevereiro.
		return false;
	} else if (mes == 2 && dia == 29 && !(ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0))) {
		// dia 29 de fevereiro em um ano não bissexto.
		return false;
	} else {
		// data válida.
		return true;
	}
};
