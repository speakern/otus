function getUser(url, valueControlName) {
    const valueControl = document.getElementById(valueControlName);
    const userDataContainer = document.getElementById('userDataContainer');
    const fullUrl = url + (valueControl? (encodeURIComponent(valueControl.value)) : '');
    fetch(fullUrl)
        .then(response => response.json())
        .then(user => userDataContainer.innerHTML = JSON.stringify(user));
}

const connect = () => {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, (frame) => {
       // setConnected(true);
        console.log('Connected: ' + frame);
        // stompClient.subscribe('/topic/response.' + $("#roomId").val(), (message) => showMessage(JSON.parse(message.body).messageStr));
        stompClient.subscribe('/topic/response/getUser.1', (message) => showMessage(message.body));
        stompClient.subscribe('/topic/response/getAllUsers.1', (message) => showMessage(message.body));
        sendMsgGetAllUsers();
    });
}

const showMessage = (message) => $("#userDataContainer").html(message)

const showListUsers = (data) => {
    let read_products_html = ``
    $.each(data, function(key, val) {
        // создание новой строки таблицы для каждой записи
        read_products_html+=`
                        <p><b>` + val.text + `</b></p>`

        if (val.type == 1) {
            $.each(val.answers, function(key1, val1) {
                read_products_html += `
                            <input name="` + val.id + `" type="radio" value="` + val1 + `">` + val1 + `</br>`
            })
        } else {
            read_products_html += `
                            <input name="` + val.id + `" maxlength="25" size="40" value=""></br>`
        }
        read_products_html+=`<br>`
    });
    //read_products_html+=`<div><button type="submit" name="AddProduct">Отправить</button></div>`;
    read_products_html+=`<p><input type="submit"></p>`;
    //            read_products_html+=`<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />`

    //read_products_html+=`</form>`;
    $("#send1").html(read_products_html);

}
const sendMsgGetUser = () => {
    stompClient.send("/app/getUser.1", {}, JSON.stringify({'messageStr': $("#userIdTextBox").val()}))
}
const sendMsgGetAllUsers = () => {
    stompClient.send("/app/getAllUsers.1", {}, JSON.stringify({'messageStr': $("#userIdTextBox").val()}))
}

$(document).ready(function () {
    connect();
});