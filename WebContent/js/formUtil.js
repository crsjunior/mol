
$.fn.existe = function()
{
	return ($(this).length != 0);
};

$(function()
{
	$('#btnSubmit').click(function() {
		executar();
	});
	if (typeof formCampos !== 'undefined') {
		for (var key in formCampos) {
			if ($('#' + key).existe()) {
				$('#' + key).keyup(function(e)
				{
					if (e.keyCode == 13) {
						executar();
					}
				});
			}
		}
		if ($('#' + Object.keys(formCampos)[0]).existe()) {
			$('#' + Object.keys(formCampos)[0]).focus();
		}
	}
});

function executar()
{
	$('#' + formNome).submit();
}
