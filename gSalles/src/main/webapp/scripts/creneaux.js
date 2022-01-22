$(document).ready(function() {
	var submitEtat = "add";
	var idSelected;
	$.ajax({
		url: "CreneauController",
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
		var heureDebut = $("#heureDebut").val();
		var heureFin = $("#heureFin").val();

		if (submitEtat == "add") {
			console.log(submitEtat);
			$.ajax({
				url: "CreneauController",
				data: { heureDebut: heureDebut, heureFin: heureFin },
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
			$.ajax({
				url: "CreneauController",
				data: { op: "update", id: idSelected, heureDebut: heureDebut, heureFin: heureFin },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					remplir(data);
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
			url: "CreneauController",
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
		var heureDebut = $(this).closest('tr').find('td').eq(1).text();
		var heureFin = $(this).closest('tr').find('td').eq(2).text();


		console.log(idSelected);
		console.log(heureDebut);
		console.log(heureFin);



		$("#heureDebut").val(heureDebut);
		$("#heureFin").val(heureFin);

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
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].heureDebut + "</td><td>" + data[i].heureFin + "</td>" + "<td><button class='delete btn btn-danger btn-rounded btn-icon' val='" + data[i].id + "'><i class='bi bi-trash'></i></button></td><td><button class='update btn btn-info btn-rounded btn-fw' val='" + data[i].id + "'><i class='bi bi-box-arrow-in-up-left'></i></button></td></tr>";
		}
		$("#content").html(ligne);
	}
});