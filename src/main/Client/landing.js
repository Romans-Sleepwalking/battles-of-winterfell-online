const socket = io.connect("http://localhost:8080", {transports : ['websocket']});

var element = """<div>
    <ul>
    <li class="current"><a href=".html" ></a></li>
<li><a href=".html"></a></li>
<li><a href=".html"></a></li>
<li><a href=".html"></a></li>
</ul>
</div>""";

function change_status(txt) {
    document.getElementById("status_msg").innerHTML = txt;
}
socket.on('connected', function (event)
    {
        change_status("Connected_to_server")
    }
);

function Login(){
    socket.emit("login", JSON.stringify({
        username: document.getElementById("logUsername"),
        password: document.getElementById("logPassword")
    }));
}

function SignUp(){
    socket.emit("signup", JSON.stringify({
        username: document.getElementById("regUsername"),
        password: document.getElementById("regPassword1")
    }));
}
