window.UI = window.UI || {};

UI.killInstance = function (data) {
    var response = JSON.parse(data);
    if (response.operation == "ok") {
        var containerId =  "#currentButton-" + parseInt(response.id);
        var textContainerId =  "#currentText-" + parseInt(response.id);
        $(textContainerId).text("Killed");
        $(containerId).removeClass("btn-success");
        $(containerId).removeClass("btn-warning");
        $(containerId).addClass("btn-danger");
    } else {
        console.log("Application error. Message is " + response.message);
    }
}

UI.startInstance = function (data) {
    var response = JSON.parse(data);
    if (response.operation == "ok") {
        var containerId =  "#currentButton-" + parseInt(response.id);
        var textContainerId =  "#currentText-" + parseInt(response.id);
        $(textContainerId).text("Running");
        $(containerId).removeClass("btn-danger");
        $(containerId).removeClass("btn-warning");
        $(containerId).addClass("btn-success");
    } else {
        console.log("Application error. Message is " + response.message);
    }
}

UI.stopInstance = function (data) {
    var response = JSON.parse(data);
    if (response.operation == "ok") {
        var containerId =  "#currentButton-" + parseInt(response.id);
        var textContainerId =  "#currentText-" + parseInt(response.id);
        $(textContainerId).text("Stopped");
        $(containerId).removeClass("btn-success");
        $(containerId).removeClass("btn-danger");
        $(containerId).addClass("btn-warning");
    } else {
        console.log("Application error. Message is " + response.message);
    }
}