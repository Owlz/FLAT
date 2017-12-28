<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Homepage</title>
	<%@include file="includes/_import.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/js/select2.min.js"></script>
</head>
<body>
<jsp:include page="includes/_header.jsp"/>


<select class="js-example-basic-single" name="film" style="width: 300px;"></select>

<script type="text/javascript">
function formatFilm (film) {
    var baseUrl = "http://image.tmdb.org/t/p/w45";
    var $film = $(
      '<span><img src="' + baseUrl +  film.poster_path + '" class="img-flag" /> ' + film.title + '</span>'
    );
    return $film;
  };

$(".js-example-basic-single").select2({
    ajax: {
      url: "ricerca",
      dataType: 'json',
      delay: 250,
      data: function (params) {
        return {
        	query: params.term
        };
      },
      processResults: function (data, params) {
        return {
          results: data.results
        };
      },
      cache: true
    },
    placeholder: 'Cerca un film',
    escapeMarkup: function (markup) { return markup; },
    minimumInputLength: 2,
    templateResult: formatFilm
  });
</script>
</body>
</html>