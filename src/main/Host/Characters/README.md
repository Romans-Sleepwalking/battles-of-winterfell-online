# The Characters Build

***

## Character init params:
* Name _(just displayed during a game)_
* Class, level _(keys to other character parameters and states + displayed during a game)_
* Attributes _(STR - strength, health coefficient; AGI - agility, damage coefficient; INT - intelligence, mana coefficient)_
* Main attribute _(buffs or debuffs max mana and max health bonuses)_
* Model path _(folder reference where character's models are stored)_


## Calculated parameters from init params:
* maxHP  _(maximum HP calculated from strength)_
* maxMP  _(maximum MP calculated from intelligence)_
* HP  _(current health points)_
* MP  _(current mana points)_


## Methods:
* HealthMana _(calculates maxHP and maxMP according to main attribute, and attribute values)_
* Restore _(refills HP or MP, or both)_
* Restate _(assign proper character state according to character's class and level)_
* Skill1
* Skill2
* Skill3
* Skill4

***
 
# Current Abilities [TASK]

| Ability  | Target    | Type    | Cost     | Description         |
|:--------:|:----------|:-------:|:--------:|:-------------------:|
| Attack   | Enemy     | Damage  | free     | Regular Attack      |
| Rush     | Enemy     | Damage  | a bit MP | Stronger Attack     |
| Heal     | Self/Ally | Restore | an ok MP | Partly restore HP   |
| Morale   | Ally Team | Buff    | a lot MP | Increase team stats |
| Awaken   | Self      | Buff    | a lot MP | Super buffed stats  |
| Shackles | Enemy     | Debuff  | an ok MP | Freeze enemy        |
| Critical | Enemy     | Damage  | a lot MP | Super damage        |


# Classes

| Class             | Attribute    | 1st Ability | 2nd Ability | 3rd Ability | 4th Ability |
|:-----------------:|:-------------|:-----------:|:-----------:|:-----------:|:-----------:|
| Knight            | Strength     | Attack      | Rush        | -           | -           |
| Crusader LVL1     | Strength     | Attack      | Heal        | -           | -           |
| Crusader LVL5     | Strength     | Attack      | Heal        | Morale      | -           |
| Crusader LVL10    | Strength     | Attack      | Heal        | Morale      | Awaken      |
| Angel             | Strength     | Attack      | Heal        | Morale      | -           |
| Necromancer LVL1  | Intelligence | Attack      | Summon      | -           | -           |
| Necromancer LVL5  | Intelligence | Attack      | Summon      | Freeze      | -           |
| Necromancer LVL10 | Intelligence | Attack      | Summon      | Freeze      | Critical    |


# Lore

| Name             | Key       | Class       | Models |
|:----------------:|:---------:|:-----------:|:------:|
| John Snow        | Snow      | Crusader    | All    |
| North Commander  | Commander | Crusader    | All    |
| Awaken Snow      | Targaryen | Angel       | All    |
| Awaken Commander | Leader    | Angel       | All    |
| North Warrior    | Warrior   | Knight      | All    |
| Zombie           | Zombie    | Knight      | All    | 
| Night King       | King      | Necromancer | All    |
| White Walker     | Walker    | Necromancer | All    |
