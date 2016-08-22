/*
 Javascript
 */


//in ms
var time;
//0 - DB; 1- Wikipedia; 2 - no source (multiple words)
var source;
function parseJsonToArray(jsonObj) {
    var result = [];
    if (typeof jsonObj == "object")
        var jsonObjParsed = jsonObj;
    else
        var jsonObjParsed = JSON.parse(jsonObj);

    //var size = Object.keys(jsonObjParsed).length;

    for (var key in jsonObjParsed) {
        if (key == "time") {
            time = jsonObjParsed[key];
        } else if (key == "source") {
            source = jsonObjParsed[key];
        } else {
            result.push([key, jsonObjParsed[key]]);
        }
    }

    return result;
};


function populateBarChart(objResult) {

    var chart = c3.generate({
        data: {
            columns: parseJsonToArray(objResult),
            type: 'bar'
        },
        axis: {
            x: {
                type: 'categorized'
            }
        },
        bar: {
            width: {
                ratio: 0.3,
            },
        }
    });
}

function sendRequestFunction() {
	document.getElementById("errorMessage").innerText = "";
    document.getElementById("source").innerText = "";
    document.getElementById("time").innerText = "";

    var xhr = new XMLHttpRequest();
    var searchText = document.getElementById("wordSearch").value;
    xhr.open("GET", "http://localhost:8080/wikiapp/getTitle/" + searchText, true);
    xhr.onload = function () {
        var response = xhr.responseText;
        if (!response) {
            document.getElementById("errorMessage").innerText = "No result was founded!";
			document.getElementById("chart").style.visibility = "hidden";
			document.getElementById("imgM").style.display = "block";
        }
        else {
			populateBarChart(response);
			document.getElementById("imgM").style.display = "none";
			document.getElementById("chart").style.visibility = "visible";
            
            document.getElementById("time").innerText = "Time: " + time + " ms;";
            if(source == 0)
                document.getElementById("source").innerText = "Source: Database";
            if(source == 1)
                document.getElementById("source").innerText = "Source: Wikipedia";
		}
    };
    xhr.send();
}

var files = [];
$(document)
    .on(
        "change",
        "#fileLoader",
        function (event) {
            files = event.target.files;
        })

$(document)
    .on(
        "click",
        "#fileSubmit",
        function () {
            processUpload();
        })

function processUpload() {
	document.getElementById("errorMessage").innerText = "";
    document.getElementById("source").innerText = "";
    document.getElementById("time").innerText = "";
    
    var oMyForm = new FormData();
    oMyForm.append("file", files[0]);

    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/wikiapp/getTitles",
        data: oMyForm,
        type: "POST",
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        success: function (result) {
            console.log(result);
            if (result == null || Object.keys(result).length === 0 ){
                document.getElementById("errorMessage").innerText = "No result was founded!";
				document.getElementById("chart").style.visibility = "hidden";
				document.getElementById("imgM").style.display = "block";
            }
            else {
                populateBarChart(result);
				document.getElementById("imgM").style.display = "none";
				document.getElementById("chart").style.visibility = "visible";

                document.getElementById("time").innerText = "Time: " + time + " ms;";
			}
        },
        error: function (result) {
            console.log("error");
			document.getElementById("chart").style.visibility = "hidden";
			document.getElementById("imgM").style.display = "block";
            document.getElementById("errorMessage").innerText = "No result was founded! Please upload a correct file";
        }

    });
}
