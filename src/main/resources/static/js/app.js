$(document).ready(function() {

		$(".datepicker").datepicker('setDate', null);

	changePageAndSize();
	btnSearch();
	// buildAndExecFilter();
	// dropdownFilter();
	saveFilter();
});

$(document).keypress(
		function(e) {
			if (e.which == 13) {
				window.location.replace("candidates/?pageSize=20&page=1&name="
						+ $("#inputSearchName").val());
			}
		});

function changePageAndSize() {

	$('#pageSizeSelect').change(
			function(evt) {
				var name = getUrlParameter('name');
				if (name != undefined) {
					window.location.replace("candidates?pageSize=" + this.value
							+ "&page=1&name=" + name);
				} else {
					window.location.replace("candidates?pageSize=" + this.value
							+ "&page=1");
				}
			});
}

function btnSearch() {
	$("#btnSearch")
			.click(
					function() {

						window.location
								.replace("/tracker/candidates/?pageSize=20&page=1&name="
										+ $("#inputSearchName").val());
					});
}

var getUrlParameter = function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
			.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
};

// function dropdownFilter() {
// var checkList = document.getElementById('list1');
// var items = document.getElementById('items');
// checkList.getElementsByClassName('anchor')[0].onclick = function(evt) {
//	
// evt.preventDefault(); // Stop navigation
// $("body").off("click.ddls");
// $("#items").toggle();
//			
// setTimeout(function(){
// $("body").on("click.ddls", function(){
// $("#items").hide();
// $("body").off("click.ddls");
// });
// }, 300);
// }
//
//	
//
// }
// /* Set 'ready' handler' */
// document.addEventListener('DOMContentLoaded', initFunc);
//
// /* When document ready, set click handlers for the filter boxes */
// function initFunc(event) {
// var filters = document.getElementsByName('tablefilter');
// for (var i = 0; i < filters.length; i++) {
// filters[i].addEventListener('click', buildAndExecFilter);
// }
// }
//
// /*
// * This function gets called when clicking on table filter checkboxes. It
// builds
// * a list of selected values and then filters the table based on that
// */
// function buildAndExecFilter() {
//
// $("input:checkbox:not(:checked)").each(function() {
// var column = "table ." + $(this).attr("name");
// $(column).hide();
// });
//
// $("input:checkbox").click(function() {
// var column = "table ." + $(this).attr("name");
// $(column).toggle();
// });
// }
// function getFilter() {
// $('.tablefilter').on('click', function() {
// var fav, favs = [];
// $('.tablefilter').each(function() { // run through each of the checkboxes
// fav = {id: $(this).attr('id'), value: $(this).prop('checked')};
// favs.push(fav);
// }
// localStorage.setItem("favorites", JSON.stringify(favs));
// console.log(localStorage.getItem('favorites'));
// });
// }
function saveFilter() {
	var checkedCol = [];
	if ($('#tablefilter1').is(':checked')) {
		checkedCol.push("FY");
		console.log(checkedCol);
	}

	if ($('#tablefilter2').is(':checked')) {
		checkedCol.push("Status");
		console.log(checkedCol);
	}

	if ($('#tablefilter3').is(':checked')) {
		checkedCol.push("Name");
		console.log(checkedCol);
	}

	if ($('#tablefilter4').is(':checked')) {
		checkedCol.push("Gender");
		console.log(checkedCol);
	}

	if ($('#tablefilter5').is(':checked')) {
		checkedCol.push("Platform");
		console.log(checkedCol);
	}

	if ($('#tablefilter6').is(':checked')) {
		checkedCol.push("Domain");
		console.log(checkedCol);
	}

	if ($('#tablefilter7').is(':checked')) {
		checkedCol.push("Skill/Project");
		console.log(checkedCol);
	}

	if ($('#tablefilter8').is(':checked')) {
		checkedCol.push("Phone Screening");
		console.log(checkedCol);
	}

	if ($('#tablefilter9').is(':checked')) {
		checkedCol.push("Interview Date");
		console.log(checkedCol);
	}

	if ($('#tablefilter10').is(':checked')) {
		checkedCol.push("COBI");
		console.log(checkedCol);
	}

	if ($('#tablefilter11').is(':checked')) {
		checkedCol.push("Offer Date");
		console.log(checkedCol);
	}

	if ($('#tablefilter12').is(':checked')) {
		checkedCol.push("Start Date");
		console.log(checkedCol);
	}

	if ($('#tablefilter13').is(':checked')) {
		checkedCol.push("Candidate Status");
		console.log(checkedCol);
	}

	if ($('#tablefilter14').is(':checked')) {
		checkedCol.push("Rejection Reason");
		console.log(checkedCol);
	}

	if ($('#tablefilter15').is(':checked')) {
		checkedCol.push("Comments");
		console.log(checkedCol);
	}

	if ($('#tablefilter16').is(':checked')) {
		checkedCol.push("Exepected Salary (EUR)");
		console.log(checkedCol);
	}

	if ($('#tablefilter17').is(':checked')) {
		checkedCol.push("Offered Salary (EUR)");
		console.log(checkedCol);
	}

	if ($('#tablefilter18').is(':checked')) {
		checkedCol.push("Career Level");
		console.log(checkedCol);
	}

	if ($('#tablefilter19').is(':checked')) {
		checkedCol.push("HR interviewer");
		console.log(checkedCol);
	}

	if ($('#tablefilter20').is(':checked')) {
		checkedCol.push("COBI Interviewer");
		console.log(checkedCol);
	}

	if ($('#tablefilter21').is(':checked')) {
		checkedCol.push("Source");
		console.log(checkedCol);
	}
};