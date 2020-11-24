let uuid = Math.random().toString(36).slice(-10);

const connect = () => {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, (frame) => {
        // setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/response/createUser.' + uuid, (message) => showMessage(message.body));
        //stompClient.subscribe('/topic/response/getAllUsers', (message) => showListUsers(JSON.parse(message.body)));
        //sendMsgGetAllUsers();
    });
}

const showMessage = (message) => $("#userDataContainer").html(message)

const showListUsers = (message) => {

    let read_products_html = ``
    $.each(message.list, function(key, val) {
        let address = (val.addressDataSet == null) ? `` : val.addressDataSet.street;
        let phone = (val.phoneDataSets.length == 0) ? `` : val.phoneDataSets[0].number;

        read_products_html+=`
                        <tr><td>` + val.id + `</td><td>`+ val.name +`</td><td>` + val.login  +`</td><td>` + address +`</td><td>` + phone +`</td></tr>`
    });

    $("#list").html(read_products_html);

}

const sendMsgCreateUser = () => {
    stompClient.send("/app/createUser." + uuid, {}, ``)
}

$(document).ready(function () {
    connect();
});