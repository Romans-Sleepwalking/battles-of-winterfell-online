
function postLogin(){
  const data = JSON.stringify({
    login: document.getElementById("logUser"),
    password: document.getElementById("logPassword")
  });

  var request = new XMLHttpRequest();
  request.onreadystatechange = function(){
    if (this.readyState === 4 && this.status === 200){
      console.log(this.response);
  }
  request.open("POST", "/LOGIN!");
  request.send(data);
  }
}

function postRegister(){
  const data = JSON.stringify({
    login: document.getElementById("regUser"),
    password: document.getElementById("regPassword")
  });

  var request = new XMLHttpRequest();
  request.onreadystatechange = function(){
    if (this.readyState === 4 && this.status === 200){
      console.log(this.response);
    }
    request.open("POST", "/REGISTER!");
    request.send(data);
  }
}