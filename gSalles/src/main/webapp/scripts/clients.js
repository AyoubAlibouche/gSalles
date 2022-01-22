$(document).ready(function() {
	var submitEtat = "add";
	var idSelected;
	$.ajax({
		url: "ClientController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			remplir(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus);
		}
	});
	$("#add").click(function() {
		var nom = $("#nom").val();
		var prenom = $("#prenom").val();
		var login = $("#login").val();
		var mdp = $("#mdp").val();

		if (submitEtat == "add") {
			console.log(submitEtat);
			$.ajax({
				url: "ClientController",
				data: { nom: nom, prenom: prenom, login: login, mdp:mdp },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					remplir(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});

		} else {
			console.log(submitEtat);
			console.log(nom);
			console.log(prenom);
			console.log(login);
			console.log(mdp);
			$.ajax({
				url: "ClientController",
				data: { op: "update", id: idSelected, nom: nom, prenom: prenom, login: login, mdp: mdp },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					remplir(data);
					submitEtat = "add";
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		}


	});
	$("#content").on("click", ".delete", function() {
		console.log("d5uuuuul l detele");
		// alert($(this).attr('val'));

		var id = $(this).closest('tr').find('td').eq(0).text();
		$.ajax({
			url: "ClientController",
			data: { op: "delete", id: id },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				remplir(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});

	});
	$("#content").on("click", ".update", function() {
		//alert($(this).attr('val'));

		idSelected = $(this).closest('tr').find('td').eq(0).text();
		var nom = $(this).closest('tr').find('td').eq(1).text();
		var prenom = $(this).closest('tr').find('td').eq(2).text();
		var login = $(this).closest('tr').find('td').eq(3).text();
		var mdp = $(this).closest('tr').find('td').eq(4).text();

		$("#nom").val(nom);
		$("#prenom").val(prenom);
		$("#login").val(login);
		$("#mdp").val(mdp);
		submitEtat = "update";


		/*   $("#add").click(function () {
	    
		$.ajax({
			url: "SalleController",
			data: {op: "update",id: id,code: code, capacite: capacite, type: type},
			type: 'POST',
			success: function (data, textStatus, jqXHR) {
	console.log("**after**");
	console.log(id);
	console.log(code);
	console.log(capacite);
	console.log(type);
				remplir(data);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});

	});*/


	});
	function remplir(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].nom + "</td><td>" + data[i].prenom + "</td><td>" + data[i].login  + "</td><td>" + data[i].mdp +"</td><td><button class='delete btn btn-danger btn-rounded btn-icon' val='" + data[i].id + "'><i class='bi bi-trash'></i></button></td><td><button class='update btn btn-info btn-rounded btn-fw' val='" + data[i].id + "'><i class='bi bi-box-arrow-in-up-left'></i></button></td></tr>";
		}
		$("#content").html(ligne);
	}
});