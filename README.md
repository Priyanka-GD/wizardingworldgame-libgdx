# wizardingworldgame-libgdx

wizarding-world-game-cpts587

This is a repo for the Project of CPTS-587 Software Design and Architecture

# Work done for Deliverable 1

1. Create the base structure of the Project -- Done
2. Created the Menu Screen, Option Screen with Arrow key options and exit screen -- Done
3. Work on the player character which should be able to respond to keyboard control and move in the corresponding 8 directions.-- Done
4. Slow speed mode activated when we press shift+ any keys -- Done
5. The hitbox, which appears as a white dot in the center of the sprite when “slow speed” mode is
   activated (see below in required features), is much smaller than the size of the whole sprite -- Done
   
# Game Script :

Wizaring world game mimicks the harry potter fiction world, in which the main charater is played against 
the antagonist of the play.
the 4 bosses in the game would be : 
-- Basillisk --  Regular enemies
-- Professor Umbridge - mid-boss
-- Lord Voldemort -- Final Boss

The entire game would last approximately 2-3 minutes, 
making up of 4 phases: regular play with grunts; mid boss attack; more grunts; and final boss attack.

Default lives of the player would be 30 and it would gradually decerease if an attack is made from the enemies.
The enemies will have specific timeframe in the game, and if the player can escape attacks from enemies and survives
till the end of the game, then the player wins.

WizardingWorldGame is based out of the Harry Potter, a young wizard and his archenemies who are all centered 
at Hogwarts School of Witchcraft and Wizardry. The focus of the gameplay is to allow the main character(Harry Potter) 
to dodge endless waves of different projectiles coming in from its archenemies. In this game, the player will control
the main character which moves around in the screen, in eight directions. The player will shoot at an array of enemies( Regular enemy [Basilisk, Dementor], 
mid-boss [Professor Umbridge] ) until reaching the boss (main archenemy, here it will be Voldemort). 
The boss will be more powerful and have more elaborated and complicated attacks. Throughout the game, 
the player can achieve power-ups which will give them privileges, to protect themselves from enemy attacks.
For a player to win the game, the main character(harry potter) must survive all the stages of the game, 
after battling with an array of archenemies. For this Project, the end goal will be to design and implement 
a stand-alone desktop application. To implement this game, I have tried to use a clean software architecture, 
to ensure that the code structure is modular, extendable, and efficient.


# Work done for Deliverable 2
1. Basillisk(enemy) as an enemy appear in a count of 5 and throw thunderbolt at the character.
2. Created Json file to read the configuration for game characters