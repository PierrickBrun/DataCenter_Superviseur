function startEquipement(_host, _dc, _idx) {

	xmlhttp = new XMLHttpRequest();
	xmlhttp.open("POST",
			"http://localhost:8080/DataCenter/jaxrs/EQ/EquipementOperation?dcId="
					+ _dc + "&idx=" + _idx + "&op=start", false);
	xmlhttp.send();
	location.reload(true);

}

function stopEquipement(_host, _dc, _idx) {
	xmlhttp = new XMLHttpRequest();
	xmlhttp.open("POST",
			"http://localhost:8080/DataCenter/jaxrs/EQ/EquipementOperation?dcId="
					+ _dc + "&idx=" + _idx + "&op=stop", false);
	xmlhttp.send();
	location.reload(true);

}

function setSeuilEquipement(_host, _dc, _idx, seuil) {
	xmlhttp = new XMLHttpRequest();
	xmlhttp.open("POST",
			"http://localhost:8080/DataCenter/jaxrs/EQ/EquipementOperation?dcId="
					+ _dc + "&idx=" + _idx + "&op=seuil&seuil=" + seuil, false);
	xmlhttp.send();
	location.reload(true);
}

function avancement(id) {
	var ava = document.getElementById("avancement_seuil" + id);
	var prc = document.getElementById("pourcentage" + id);
	prc.innerHTML = ava.value + "%";
}

function modif(id, val, _host, _dc, _idx) {
	var ava = document.getElementById("avancement_seuil" + id);
	if ((ava.value + val) <= ava.max && (ava.value + val) > 0) {
		console.log("SWAG" + ava.value);
		ava.value += val;
		console.log(ava.value);
		setSeuilEquipement(_host, _dc, _idx, ava.value);
	}

	avancement(id);
}