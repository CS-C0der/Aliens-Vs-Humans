# Introduction
Aliens vs Humans is a simple command line game intended to serve as a portfolio project. 

There are a few things i wanted to put emphasis on:
- Test Driven Design (Test first)
- multithreading
- object oriented design

# How the game works
Obviously aliens are going to fight against humans. At the beginning we set the size of the teams. There should be the same amount of aliens/humans per team. To make the game a bit more interesting we also add cats to the battlefield. Cats are cute but are not very loyal to humans. So cats are randomly assigned to either team human or team alien. 

Then the "war" can begin. Each team-member starts a fight 1 on 1 against a member of the opposing team. Each 1 on 1 fight is represented by a thread. As soon as one fight is over the winner should start a new fight (thread) immedeately.

## Races
There are different Alien races:

- **Facehugger**: Inspired by the computer game half life. See here [here](https://half-life.fandom.com/wiki/Headcrab). When a facehugger defeats a human they mutate to a Zombie.
- **Zombie**: See Facehugger. There are no Zombies at the beginning of the game. They only occur when a Facehugger defeats a Human.
- **Klingon**: Can carry Weapons. Standard Weapon: Phaser.
- **Borg**: Can carry Weapons. Standard Weapon: Phaser. Can assimilate Humans.
- **Alf**: likes to eat Cats!

**Humans** aren't considered a race. They also use Weapons and in addition to that carry armor (100 armor points). Armor works just like additional Hitpoints.

**Cats**: have 9 lives! If the hitpoints of a cat get below 0 the cat loses a life and starts a new one with 100 hitpoints. 

## Rules
To make the realisation a bit more challeging there are some special rules. In general every Entity (Human, Alien, Cat) has 100 Hitpoints. Humans and some Alien Races can carry weapons. Each weapon does different damage. The weapons are assigned randomly at the beginning. The Entities attack in turns. The entity of team human atacks first. Then team alien, then again team human and so on...

### Further rules are:

- Facehuggers always loose against cats (because they can't grab the face of the cat as cats are too small)
- If a Facehugger fights a human, there is a 50% chance it wins the fight. If Facehugger wins, Human and Facehugger mutate to a Zombie and the Zombie takes the weapon from the Human.
- If a Borg wins a fight against a Human, the Human is assimilated and respawns as a new Borg with 100 hitpoints on team Alien. Cats can not be assimilated. 
- Alf always wins against Cats because he eats Cats! But always looses against Humans because he really isn't a fighter at all.

