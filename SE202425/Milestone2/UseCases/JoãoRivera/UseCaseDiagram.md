Use case name: Select position 1
Use case description: The Player executes this command using //pos1[coordinates]. The WorldEdit System processes the command and executes it, selecting the position for the first corner of the cuboid, if no argument is passed it defaults to player position.
Main actor: Player
Secondary actor: None
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Use case name: Generate hollow cylinder
Use case description: The Player executes this command using //hcyl <pattern> <radii> [height]. The WorldEdit System processes the command and executes it, generating the hollow cylinder with the block, radius and height specified by the Player.
Main actor: Player
Secondary actor: None
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Use case name: Bind a tool to an item
Use case description: The Player executes this command using the /tool <cycler|navwand|none|floodfill|deltree|farwand|selwand|stacker|repl|tree|lrbuild|info> command. The WorldEdit System processes the command and executes it, transforming the item into the tool specified by the Player.
Main actor: Player
Secondary actor: None
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Use case name: Execute a CraftScript
Use case description: The Player executes a CraftScript using the /cs <filename> [args...] command. The WorldEdit System processes the command, verifies the player's permissions, loads the specified script file, and executes it. If successful, the system performs the actions defined in the script and provides feedback to the player
Main actor: Player
Secondary actor: None
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Use case name: Restore the selection from a snapshot
Use case description: The Player executes this command using the /restore [snapshot] command. The WorldEdit System processes the command and executes it, restoring the selection from the snapshot the Player provided.
Main actor: Player
Secondary actor: None
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Use case diagram:![image](https://github.com/user-attachments/assets/e0269afe-58d5-48bd-b8c8-8b1a661ded7d)


