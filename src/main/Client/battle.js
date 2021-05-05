let visible_buttons = [];
let chat = "";
let character = {
    id: "loading",
    side: "loading",
    name: "loading",
    class: "loading",
    img: "loading",
    state: "loading",
    HP: "loading",
    maxHP: "loading",
    MP: "loading",
    maxMP: "loading",
    abi1: {
        num: "loading",
        status: "loading",
        name: "loading",
        description: "loading",
        target: "loading",
        effect: "loading",
        cost: "loading",
    },
    abi2: {
        num: "loading",
        status: "loading",
        name: "loading",
        description: "loading",
        target: "loading",
        effect: "loading",
        cost: "loading",
    },
    abi3: {
        num: "loading",
        status: "loading",
        name: "loading",
        description: "loading",
        target: "loading",
        effect: "loading",
        cost: "loading",
    },
    abi4: {
        num: "loading",
        status: "loading",
        name: "loading",
        description: "loading",
        target: "loading",
        effect: "loading",
        cost: "loading",
    }
}
let chosen_abi = "";
let chosen_target = "";

function updateChat(msg){  // updates a chat with a new message + time code
    const date = new Date()
    chat += date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+" | " + msg + "<br />"
    document.getElementById("game_console").innerHTML = (chat);
}

// Circles

function showCircle(){
    document.getElementById("circle-"+character["id"]).style.visibility = "visible";
}
function hideCircle(){
    document.getElementById("circle-"+character["id"]).style.visibility = "hidden";
}

// Ability-related functions

function selectSkillListener(skill){
    function display(skill){
        hideTargetChoices()
        const url = "img/models/" + character.side + "/" + character.img + "/" + skill + ".png"
        console.log(url)
        document.getElementById(("char-"+character.id)).src = url;
        displayTargetChoices(chosen_abi["target"])
    }
    if ((skill === "1" && character.abi1.status === "mana") ||
        (skill === "2" && character.abi2.status === "mana") ||
        (skill === "3" && character.abi3.status === "mana") ||
        (skill === "4" && character.abi4.status === "mana")){
        updateChat("Not enough mana!")
    }
    else if (skill === "1" && character.abi1.status !== "lock"){
        chosen_abi = character.abi1
        display("Skill_1")
    }
    else if (skill === "2" && character.abi2.status !== "lock"){
        chosen_abi = character.abi2
        display("Skill_2")
    }
    else if (skill === "3" && character.abi3.status !== "lock"){
        chosen_abi = character.abi3
        display("Skill_3")
    }
    else if (skill === "4" && character.abi4.status !== "lock"){
        chosen_abi = character.abi4
        display("Skill_4")
    }
}
function updateSkills(){
    const skillSet = {1: character["abi1"], 2: character["abi2"], 3: character["abi3"], 4: character["abi4"]}

    for (i = 1; i < 5; i++){
        let abi = skillSet[i]
        let img = null

        if (character["state"] === "Frozen" && i === 1){
            img = "img/skills/warm"
        }
        else if (character["state"] === "Frozen" && i !== 1) {
            img = "img/skills/frost"
        }
        else {
            img = "img/skills/" + character["class"] + "/" + i
        }
        if (abi["status"] === "mana"){
            img += "_mana"
        }
        img = "URL(" + img + ".png"
        document.getElementById("skill-icon-"+i).style.backgroundImage = img;
        document.getElementById("skill-name-"+i).textContent = abi["name"];
        let mana_cost = " ";
        if (abi["cost"] !== "0" && abi["cost"] !== ""){
            mana_cost = "(" + abi["cost"] +"MP)"
        }
        document.getElementById("skill-cost-"+i).textContent = mana_cost;
        document.getElementById("skill-descr-"+i).textContent = abi["description"];
    }
}

// Target-related abilities

function displayTargetChoices(options){  // displays target choices on characters
    function display(btn){
        document.getElementById(btn).style.visibility = "visible";
        visible_buttons.push(btn);
    }
    if (options.includes(1)){
        display("btn1")
    }
    if (options.includes(2)){
        display("btn2")
    }
    if (options.includes(3)){
        display("btn3")
    }
    if (options.includes(4)){
        display("btn4")
    }
    if (options.includes(5)){
        display("btn5")
    }
    if (options.includes(6)){
        display("btn6")
    }
    if (options.includes(7)){
        display("btn7")
    }
    if (options.includes(8)){
        display("btn8")
    }
}
function hideTargetChoices(){  // hides target choices on characters
    function hide(btn){
        document.getElementById(btn).style.visibility = "hidden";
    }
    visible_buttons.forEach(hide)
    visible_buttons = []
}
function selectTargetListener(target){  // selects a target an calls "action send"
    chosen_target = target
    hideTargetChoices()
    hideCircle()
    let url = document.getElementById(("char-"+chosen_target)).src
    const urlPattern = /(img\/models\/[a-zA-Z]\/[a-zA-Z]+\/[a-zA-Z_1-4]+.png)/;
    const statePattern = /([a-zA-Z_1-4]+).png/;
    const match = url.match(urlPattern)[1]
    url = match.replace(match.match(statePattern)[1], chosen_abi["effect"])
    document.getElementById(("char-"+target)).src = url;
    character["abi1"]["status"] = "lock"
    character["abi2"]["status"] = "lock"
    character["abi3"]["status"] = "lock"
    character["abi4"]["status"] = "lock"
    sendAction()
}

// Server communications

const socket = io.connect("http://localhost:8080/", {transports: ['websocket']});

socket.on('msg', function (message) {
    updateChat(message)
});

socket.on('upd', function (jsonUpdData) {
    console.log('update received')
    console.log(jsonUpdData)
    const updData = JSON.parse(jsonUpdData)
    updateChat(updData["msg"])
    const characters = {
        1: updData["P1"]["1"],
        2: updData["P1"]["2"],
        3: updData["P1"]["3"],
        4: updData["P1"]["4"],
        5: updData["P2"]["1"],
        6: updData["P2"]["2"],
        7: updData["P2"]["3"],
        8: updData["P2"]["4"],
    }
    for (i = 1; i < 9; i++){
        let char = characters[i]
        let url = "img/models/" + char["side"] + "/" + char["img"] + "/" + char["state"] + ".png"
        document.getElementById(("char-"+i)).src = url;
    }
});

socket.on('turn', function (jsonTurnData){
    character = JSON.parse(jsonTurnData)
    showCircle()
    updateSkills()
    updateChat("Your turn, " + character["name"] + "! HP: " + character["HP"] + ", MP: " + character["MP"])
});

function sendAction(){
    console.log("sending a turn!")
    socket.emit("action", JSON.stringify({"charID": parseInt(character["id"]), "target": parseInt(chosen_target), "skill": chosen_abi.num}))
}
