/**
 * Inclui um arquivo de script ao documento.
 * @param String src Localização do arquivo de script
 */
function include_script(src)
{
	if (file_exists(src)) {
		var script = document.createElement('script');
		script.src = src;
		script.type = "text/javascript";
		document.head.appendChild(script);
	} else {
		alert("Arquivo de script não foi encontrado!\n\nURL do arquivo: '" + src + "'");
	}
}

/**
 * Verifica se um arquivo existe.
 * @param url String com a URL do arquivo
 * @returns {Boolean} True se o arquivo existe, caso contrário, False
 */
function file_exists(url)
{
	if (url) {
		var req = new XMLHttpRequest();
		req.open("GET", url, false);
		req.send();
		return req.status == 200;
	} else {
		return false;
	}
}

/**
 * Verifica se um elemento existe.
 */
$.fn.existe = function()
{
	return ($(this).length != 0);
};


function setStatus(conteudo_html, classe, tempo_exibicao)
{
	if (classe == null) {
		classe = '';
	}
	tempo_exibicao = (tempo_exibicao == null) ? 4000 : (tempo_exibicao * 1000);
	
	$('#status_form').toggleClass(classe);
	$('#status_form').html(conteudo_html);
	if (tempo_exibicao > 0) {
		var timer = setInterval(function() {
				$('#status_form').html('');
				clearInterval(timer);
			},
			tempo_exibicao);
	}
}


function corFundo(cor)
{
	if (cor == null) {
		$("body").css("background-color", "");
	} else {
		$("body").css("background-color", cor);
	}
}

function corBordaFieldset(cor)
{
	if (cor == null) {
		$('fieldset').css('border-color', '');
	} else {
		$('fieldset').css('border-color', cor);
	}
}

function corLegend(foreCor, backCor)
{
	if (foreCor == null) {
		$('legend').css('color', '');
	} else {
		$('legend').css('color', foreCor);
	}
	if (backCor == null) {
		$('legend').css('background-color', '');
	} else {
		$('legend').css('background-color', backCor);
	}
}

/**
 * Converte uma cor no formato RGB 'rgb(xxx, xxx, xxx)' para o formato HEX '#xxxxxx'.
 * @param cor_rgb String com a cor no formato RGB
 * @returns String com a cor no formato HEX
 */
function rgb_to_hex(cor_rgb)
{
	var hex_rgb = cor_rgb.match(/^rgba?\((\d+),\s*(\d+),\s*(\d+)\)$/);
	function hex(x) { return ('0' + parseInt(x).toString(16)).slice(-2); }
	if (hex_rgb) {
		return '#' + hex(hex_rgb[1]) + hex(hex_rgb[2]) + hex(hex_rgb[3]);
	} else {
		return cor_rgb;
	}
}

$.fn.getHexColor = function() {
	var hex_rgb = this.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	function hex(x) { return ('0' + parseInt(x).toString(16)).slice(-2); }
	if (this) {
		return '#' + hex(hex_rgb[1]) + hex(hex_rgb[2]) + hex(hex_rgb[3]);
	} else {
		return this;
	}
};

var ColorToHex = function(color) {
	var hex = '#';
	var pos = (color.indexOf('rgba') == 0) ? 5 : 4;
	$.each(color.substring(pos).split(','), function(i, str) {
		var h = ($.trim(str.replace(')', '')) * 1).toString(16);
		hex += (h.length == 1) ? '0' + h : h;
	});
	return hex;
};
