Long Parameter List
Code Snipet:

![Captura de Ecrã (30)](https://github.com/user-attachments/assets/d92dbc84-e8a6-4084-b548-aeb825aa5c87)

Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/command/GenerationCommands.java

Explanation of the rationale for identifying this code smell: In this case, the use of this many arguments
could and should be avoided. In this case, it would be more clear to read if instead of passing 4 boolean variables, 
it was created and object called "restrictions" that stored the variables.


Message Chain
Code Snipet:

![Captura de Ecrã (31)](https://github.com/user-attachments/assets/6722a17b-5922-44a3-bd23-ebf50f9213d9)

Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/command/GeneralCommands.java

Explanation of the rationale for identifying this code smell: This is present in many parts of the overall code.
This is one of the examples that is more problematic. The issue could be avoided if the Class in question only
talked to neighbour Classes.


Primitive Obsession
Code Snipet:

![Captura de Ecrã (36)](https://github.com/user-attachments/assets/e43c9145-1b26-4ddd-878e-4af703274204)

Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java

Explanation of the rationale for identifying this code smell: Using 4 for loops makes the code complicated and
hard to read. The cause of this problem is primitive obsession. The cooder is using a loop for each axis (x,y,z).
Instead of doing that, it would be much easier if he used and object that could be called "Point". This could store the
information needed and it would only take a while loop to run across all combinations of x, y and z (Starting from 
(0,0,0) and ending in (x,y,z)). 
