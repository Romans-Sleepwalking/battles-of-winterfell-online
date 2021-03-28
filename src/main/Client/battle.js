function changeIcon(){
    document.getElementById("skill-icon-1").style.backgroundImage = "url('img/skills/Crusader/3.jpg')";
}

function Action(skill){
    if (skill === "1") {
        document.getElementById("char-1").src = "img/models/L/Snow/Skill 1.png";
    }
    else if (skill === "2") {
        document.getElementById("char-1").src = "img/models/L/Snow/Skill 2.png";
    }
    else if (skill === "3") {
        document.getElementById("char-1").src = "img/models/L/Snow/Skill 3.png";
    }
    else if (skill === "4") {
        document.getElementById("char-1").src = "img/models/L/Snow/Skill 4.png";
    }
}

function selectSlot(num){
    console.log("Slot " + num + " selected!")
}

