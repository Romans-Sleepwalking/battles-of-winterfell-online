let visible_buttons = [];
let chat = "";
let char = "";
let chosen_abi = "";


function updChat(msg){
    const date = new Date()
    chat += date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+" | " + msg + "<br />"
    document.getElementById("game_console").innerHTML = (chat);
}


function displayChoices(options){
    console.log(options.toString())
    function display(btn){
        document.getElementById(btn).style.visibility = "visible";
        visible_buttons.push(btn);
    }
    if (options.includes("1")){
        display("btn1")
    }
    if (options.includes("2")){
        display("btn2")
    }
    if (options.includes("3")){
        display("btn3")
    }
    if (options.includes("4")){
        display("btn4")
    }
    if (options.includes("5")){
        display("btn5")
    }
    if (options.includes("6")){
        display("btn6")
    }
    if (options.includes("7")){
        display("btn7")
    }
    if (options.includes("8")){
        display("btn8")
    }
}


function hideChoices(){
    function hide(btn){
        document.getElementById(btn).style.visibility = "hidden";
    }
    visible_buttons.forEach(hide)
    visible_buttons = []
}


function selectTargetListener(target){
    updChat("Slot " + target + " selected!")
    hideChoices()
    const urlPattern = /(img\/models\/[a-zA-Z]\/[a-zA-Z]+\/[a-zA-Z\-]+.png)/;
    let url = document.getElementById(("char-"+target)).src
    //updChat(url)
    const match = url.match(urlPattern)[1]
    //updChat(match.toString())
    url = url.replace(match, chosen_abi.effect)
    document.getElementById(("char-"+target)).src = match;
    //updChat(url)
    // socket.emit("command");
}


function selectSkillListener(skill){
    function display(skill){
        console.log(char)
        hideChoices()
        const url = "img/models/" + char.side + "/" + char.IMG + "/" + skill + ".png"
        document.getElementById(("char-"+char.num)).src = url;
        displayChoices(chosen_abi.target)
    }
    if ((skill === "1" && char.abi1.status === "mana") ||
        (skill === "2" && char.abi2.status === "mana") ||
        (skill === "3" && char.abi3.status === "mana") ||
        (skill === "4" && char.abi4.status === "mana")){
        updChat("Not enough mana!")
    }
    else if (skill === "1" && char.abi1.status !== "lock"){
        chosen_abi = char.abi1
        display("Skill_1")
    }
    else if (skill === "2" && char.abi2.status !== "lock"){
        chosen_abi = char.abi2
        display("Skill_2")
    }
    else if (skill === "3" && char.abi3.status !== "lock"){
        chosen_abi = char.abi3
        display("Skill_3")
    }
    else if (skill === "4" && char.abi4.status !== "lock"){
        chosen_abi = char.abi4
        display("Skill_4")
    }
}


function updSkills(num, abi, Class){
    let img = "img/skills/" + Class + "/" + num
    if (abi.status === "mana"){
        img += "_mana"
    }
    console.log(img)
    img = "URL(" + img + ".png"
    document.getElementById("skill-icon-"+num).style.backgroundImage = img;
    document.getElementById("skill-name-"+num).textContent = abi.name;
    var mana_cost = " "
    if (abi.cost !== "0" && abi.cost !== ""){
        mana_cost = "(" + abi.cost +"MP)"
    }
    console.log(mana_cost)
    document.getElementById("skill-cost-"+num).textContent = mana_cost;
    document.getElementById("skill-descr-"+num).textContent = abi.description;
}


function nextTurn(data){
    updChat(data.msg)

    char = data
    updChat(char.username + "'s turn!")
    updChat(char.name + " the " + char.Class + ". HP:" + char.HP + "/" + char.maxHP +
        " and MP: " + char.MP + "/" + char.maxMP)

    updSkills("1", char.abi1, data.Class)
    updSkills("2", char.abi2, data.Class)
    updSkills("3", char.abi3, data.Class)
    updSkills("4", char.abi4, data.Class)
}




// Test Variables
greeting1 = {msg: "Greetings from left player (wins=0, loses=0)! Good luck, have fun!"}
greeting2 = {msg: "Greetings from right player (wins=0, loses=0)! Have a nice game!"}

msg1 = {
    username: "Left player",
    msg: "Game begins!",
    num: 1,
    name: "John Snow",
    Class: "Crusader",
    img: "Snow",
    state: "Passive",
    HP: "100",
    maxHP: "100",
    MP: "70",
    maxMP: "70",
    side: "L",
    abi1: {
        status: "ok",
        name: "Attack",
        description: "",
        target: ["5", "6", "7", "8"],
        effect: "Attacked",
        cost: "0",
    },
    abi2: {
        status: "ok",
        name: "Heal",
        description: "easy +hp",
        target: ["2", "3", "4"],
        effect: "Healed",
        cost: "0",
    },
    abi3: {
        status: "ok",
        name: "Morale",
        description: "Cheer up the team",
        target: [],
        effect: "Attacked",
        cost: "40",
    },
    abi4: {
        status: "mana",
        name: "Jagerbomb",
        description: "RedBull gives you wings",
        target: [],
        effect: "Passive",
        cost: "80",
    }
}

msg2 = {
    username: "right player",
    msg: "What a move!",
    num: 7,
    name: "Hendrix",
    Class: "Knight",
    img: "Zombie",
    state: "Passive",
    HP: "40",
    maxHP: "50",
    MP: "42",
    maxMP: "45",
    side: "R",
    abi1: {
        status: "ok",
        name: "Attack",
        description: "",
        target: ["1", "2", "3", "4"],
        effect: "Attacked",
        cost: "0",
    },
    abi2: {
        status: "ok",
        name: "Rush",
        description: "Powerful slash",
        target: ["1", "2", "3", "4"],
        effect: "Attacked",
        cost: "15",
    },
    abi3: {
        status: "lock",
        name: "",
        description: "",
        target: [],
        effect: "",
        cost: "",
    },
    abi4: {
        status: "lock",
        name: "",
        description: "",
        target: [],
        effect: "",
        cost: "",
    }
}

nextTurn(msg1)

/*
socket.on('greeting', function (jsonGameState) {
    console.log(jsonGameState);


socket.on('update', function (jsonGameState) {
    console.log(jsonGameState);
    // TODO
});
*/
