const socket = io.connect("http://localhost:8080", {transports : ['websocket']});

function change_status(txt) {
    document.getElementById("status_msg").innerHTML = txt;
}
socket.on('connected', function (event)
    {
        change_status("Connected_to_server")
    }
);

const party = {  // Complete user data
    username: "Martin",
    wins: 10,
    loses: 3,
    side: "humans"
}

let images = {char1: "Snow", char2: "Commander", char3: "Warrior", char4: "Warrior"};


function swapSide(){  // Change the default side characters
    if (party.side === "humans") {
        party.side = "undeads"
        // Main Hero
        document.getElementById("name1").innerText = "The Night King";
        document.getElementById("class1").innerText = "Necromancer";
        document.getElementById("img1").src = "img/models/L/King/Passive.png";
        images.char1 = "King"
        // Commander
        document.getElementById("name2").innerHTML = "White Walker";
        document.getElementById("class2").innerHTML = "Necromancer";
        document.getElementById("img2").src = "img/models/L/Walker/Passive.png";
        images.char2 = "Walker"
        // Knight 1
        document.getElementById("name3").innerHTML = "Fallen Warrior";
        document.getElementById("class3").innerHTML = "Knight";
        document.getElementById("img3").src = "img/models/L/Zombie/Passive.png";
        images.char3 = "Zombie"
        // Knight 2
        document.getElementById("name4").innerHTML = "Hangman";
        document.getElementById("class4").innerHTML = "Knight";
        document.getElementById("img4").src = "img/models/L/Zombie/Passive.png";
        images.char4 = "Zombie"
    }
    else if (party.side === "undeads") {
        party.side = "humans"
        // Main Hero
        document.getElementById("name1").innerHTML = "John Snow";
        document.getElementById("class1").innerHTML = "Crusader";
        document.getElementById("img1").src = "img/models/R/Snow/Passive.png";
        images.char1 = "Snow"
        // Commander
        document.getElementById("name2").innerHTML = "Black Sparrow";
        document.getElementById("class2").innerHTML = "Crusader";
        document.getElementById("img2").src = "img/models/R/Commander/Passive.png";
        images.char2 = "Commander"
        // Knight 1
        document.getElementById("name3").innerHTML = "Flock";
        document.getElementById("class3").innerHTML = "Knight";
        document.getElementById("img3").src = "img/models/R/Warrior/Passive.png";
        images.char3 = "Warrior"
        // Knight 2
        document.getElementById("name4").innerHTML = "Winston";
        document.getElementById("class4").innerHTML = "Knight";
        document.getElementById("img4").src = "img/models/R/Warrior/Passive.png";
        images.char4 = "Warrior"
    }
}

function Fight(){  // Initialize a fight by sending data to the server
    const char1 = {
        Name: document.getElementById("name1").innerHTML,
        Class: document.getElementById("class1").innerHTML,
        IMG: images.char1,
        STR: parseInt(document.getElementById("STR1").value),
        AGI: parseInt(document.getElementById("AGI1").value),
        INT: parseInt(document.getElementById("INT1").value)
    }
    const char2 = {
        Name: document.getElementById("name2").innerHTML,
        Class: document.getElementById("class2").innerHTML,
        IMG: images.char2,
        STR: parseInt(document.getElementById("STR2").value),
        AGI: parseInt(document.getElementById("AGI2").value),
        INT: parseInt(document.getElementById("INT2").value)
    }
    const char3 = {
        Name: document.getElementById("name3").innerHTML,
        Class: document.getElementById("class3").innerHTML,
        IMG: images.char3,
        STR: parseInt(document.getElementById("STR3").value),
        AGI: parseInt(document.getElementById("AGI3").value),
        INT: parseInt(document.getElementById("INT3").value)
    }
    const char4 = {
        Name: document.getElementById("name4").innerHTML,
        Class: document.getElementById("class4").innerHTML,
        IMG: images.char4,
        STR: parseInt(document.getElementById("STR4").value),
        AGI: parseInt(document.getElementById("AGI4").value),
        INT: parseInt(document.getElementById("INT4").value)
    }

    const result = 150 - char1.STR - char1.AGI - char1.INT - char2.STR - char2.AGI - char2.INT - char3.STR - char3.AGI - char3.INT
    console.log(result.toString())

    if (result < 0) {
        document.getElementById("points").innerHTML = "Points Balance: " + result + "   You have spent too much!"
    }
    else if (result > 0) {
        document.getElementById("points").innerHTML = "Points Balance: " + result + "   Spend more!"
    }
    else {
        document.getElementById("points").innerHTML = "Points Balance: " + result + "   Fight Initialization!"
        const json = JSON.stringify({party, char1, char2, char3, char4});
        console.log(json)
        socket.emit("fight", json)
    }
}
