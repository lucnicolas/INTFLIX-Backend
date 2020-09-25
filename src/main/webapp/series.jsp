<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="./inc/style.css" />
<script type="text/javascript" src="./inc/GestionSeries.js"></script>
<title>Mes séries</title>
</head>
<body>
	<h1>Mes séries</h1>
	<span class="erreur"><c:out value="${ erreur }" /></span>
	<form method="post" id="newSerieForm" action="./creerSerie" onSubmit="return askSerieName()">
		<input type=hidden name="nomNvlleSerie" id="nomNvlleSerie" />
		<button type="submit" title="Créer une nouvelle série">
			<img src="./img/Add.png">
		</button>
	</form>
	<table style="padding: 20px; border: 1px;">
		<c:forEach var="serie" items="${ series }">
			<tr>
				<td colspan="3" class="${ serie.seen } Serie">${ serie.name }</td>
				<td class="${ serie.seen }">
					<form method="post" action="./creerSaison">
						<input type="hidden" name="serie" value="${ serie.id }" />
						<button type="submit" title="Créer une nouvelle saison">
							<img src="./img/Add.png">
						</button>
					</form>

					<form method="post" action="./supprimerSerie">
						<input type="hidden" name="serie" value="${ serie.id }" />
						<button title="Supprimer la série">
							<img src="./img/Remove.png">
						</button>
					</form>

					<button id="${ serie.name }_bt" title="Masquer le contenu de la série" onclick="masquer('${ serie.name }')">
						<img id="${ serie.name }_img" src="./img/Up.png">
					</button>
				</td>
			</tr>
			<c:forEach var="season" items="${ serie.season }">
				<tr class="${ serie.name }_saison">
					<td class="filler">&nbsp;</td>
					<td class="${ season.seen } Saison" colspan="2">Saison N°${ season.number }</td>
					<td class="${ season.seen }">
						<form method="post" action="./creerEpisode" onSubmit="return askEpisodeName('${ serie.name }',${ season.number })">
							<input type="hidden" name="saison" value="${ season.id }" />
							<input type=hidden name="nomEpisode" id="${ serie.name }${ season.number }" />
							<button type="submit" title="Créer un nouvel épisode">
								<img src="./img/Add.png">
							</button>
						</form>
						<form method="post" action="./supprimerSaison">
							<input type="hidden" name="serie" value="${ serie.id }" />
							<input type="hidden" name="saison" value="${ season.id }" />
							<button title="Supprimer la saison">
								<img src="./img/Remove.png">
							</button>
						</form>
						<button id="${ serie.name }${ season.number }_bt" title="Masquer le contenu de la saison"
							onclick="masquerSaison('${ serie.name }${ season.number }')"
						>
							<img id="${ serie.name }${ season.number }_img" src="./img/Up.png">
						</button>

					</td>
				</tr>
				<c:forEach var="episode" items="${ season.episodes }">
					<tr class="${ serie.name }_saison ${ serie.name }${ season.number }_ep">
						<td class="filler">&nbsp;</td>
						<td class="filler">&nbsp;</td>
						<td class="${ episode.seen } Episode">${ episode.title }</td>
						<td class="${ episode.seen }">
							<form method="post" action="./etat">
								<input type=hidden name="episode" value="${ episode.id }" />
								<button type="submit" title="changer d'état">
									<img alt="changer d'état" src="./img/EyeChange_small.png">
								</button>
							</form>
							<form method="post" action="./supprimerEpisode">
								<input type="hidden" name="saison" value="${ season.id }" />
								<input type=hidden name="episode" value="${ episode.id }" />
								<button title="Supprimer l'épisode">
									<img src="./img/Remove.png">
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</table>
</body>
</html>