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
        setConnected(true);
        console.log('Connected: ' + frame);
        // stompClient.subscribe('/topic/response.' + $("#roomId").val(), (message) => showMessage(JSON.parse(message.body).messageStr));
        stompClient.subscribe('/topic/response.1', (message) => showMessage(message.body));
    });
}

const showMessage = (message) => $("#chatLine").append("<tr><td>" + message + "</td></tr>")