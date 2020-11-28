let uuid = Math.random().toString(36).slice(-10);

const connect = () => {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, (frame) => {
       // setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/response/getUser.' + uuid, (message) => showUser(message.body));
        stompClient.subscribe('/topic/response/getAllUsers', (message) => showListUsers(JSON.parse(message.body)));
        stompClient.subscribe('/topic/response/createUser.' + uuid, () => usersRefresh());
        sendMsgGetAllUsers();
    });
}

const showUser = (message) => {
    $("#userDataContainer").html(message);
}

const usersRefresh = () => {
    sendMsgGetAllUsers();
}

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

const sendMsgGetUser = () => {
    stompClient.send("/app/getUser." + uuid, {}, JSON.stringify({'messageStr': $("#userIdTextBox").val()}))
}
const sendMsgGetAllUsers = () => {
    stompClient.send("/app/getAllUsers")
}
const sendMsgCreateUser = () => {
    const user = {};

    user.name = $("#name").val();
    user.login = $("#login").val();
    user.password = $("#password").val();
    user.phone1 = $("#phone1").val();
    user.phone2 = $("#phone2").val();
    user.address = $("#address").val();

    stompClient.send("/app/createUser." + uuid, {},  JSON.stringify(user));
    //stompClient.send("/app/getUser." + uuid, {}, JSON.stringify({'messageStr': $("#userIdTextBox").val()}))

}


$(document).ready(function () {
    connect();
});