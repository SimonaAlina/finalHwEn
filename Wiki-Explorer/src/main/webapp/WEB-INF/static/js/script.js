
var data1 = [
    ['data1', 1030],
    ['data2', 2130, 2100, 2140, 2200, 2150, 1850]
//           ['data1', 30, 200, 100, 400, 150, 250],
//           ['data2', 130, 100, 140, 200, 150, 50]
];
function populate() {
    var chart = c3.generate({
        data: {
            columns: data1,
            type: 'bar',
            onclick: function (d, element) {
                console.log("onclick", d, element);
            },
            onmouseover: function (d) {
                console.log("onmouseover", d);
            },
            onmouseout: function (d) {
                console.log("onmouseout", d);
            }
        },
        axis: {
            x: {
                type: 'categorized'
            }
        },
        bar: {
            width: {
                ratio: 0.3,
//            max: 30
            },
        }
    });
}

function  fff() {
    $.ajax({
        url: "rest/api/crunchifyService/jsonpost",
        method: "GET",
        // data: JSON.stringify(jsonObj),
        dataType: 'json',
        contentType: "application/json",
        success: function(result,status,jqXHR ){
            populate(result);
        }
    });
}