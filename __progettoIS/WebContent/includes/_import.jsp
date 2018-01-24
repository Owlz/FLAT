<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/js/select2.min.js"></script>

<script type="text/javascript">
function formatFilm (film) {
	if (film.loading) return "Sto cercando..";
	
    var baseUrl = "http://image.tmdb.org/t/p/w45";
    var $film = $(
      '<span><img src="' + baseUrl +  film.locandina + '" class="img-flag" /> ' + film.titolo + '</span>'
    );
    if(film.titolo == "Errore richiesta"){
    	$film = $('<span><img src="img/imagefound_small.png" class="img-flag" /> ' + film.titolo + '</span>');
    }
    return $film;
  };
  
$(".js-example-basic-single")
	.select2({
		ajax: {
			url: "ricerca",
			dataType: 'json',
			delay: 250,
			data: function(params) {
				return {
					query: params.term
				};
			},
			processResults: function(data, params) {
				return {
					results: data.results
				};
			},
			cache: true
		},
		placeholder: 'Cerca un film',
		escapeMarkup: function(markup) {
			return markup;
		},
		minimumInputLength: 2,
		templateResult: formatFilm
	})
	.on("select2:selecting", function(e) {
		if(e.params.args.data.id == 0) window.location.href = "";
		else window.location.href = "film?id=" + e.params.args.data.id;
	});
</script>