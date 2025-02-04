Adapter pattern

Code snippet:

![Captura de Ecrã (38)](https://github.com/user-attachments/assets/a3d508ec-3b04-4d15-9de9-07732cd1f891)

Class Diagram:

![Captura de Ecrã (37)](https://github.com/user-attachments/assets/426d8a15-ec64-49fe-b99f-bd7b465c3dd7)

Exact location: worldedit-bukkit/src/main/java/com/sk89q/worldedit/bukkit/BukkitAdapter.java

Discussion of the rationale for identifying: The BukkitAdapter, as a Adapter pattern, helps the communication
between two incompatible systems. In this case the systems are the Bukkit system and the WorldEdit. The Strategy 
acts as a bridge, making it easier to integrate new or existing code without modifying original implementations.


Iterator pattern

Code snippet:

![Captura de Ecrã (40)](https://github.com/user-attachments/assets/84b96917-ca25-45a5-bd61-288f17b68e60)

Class Diagram:

![Captura de Ecrã (41)](https://github.com/user-attachments/assets/328fe2fc-c215-4ac9-bca3-4b117d721e8c)

Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/regions/Polygonal2DRegion.java

Discussion of the rationale for identifying: This code defines an strategy for iterating over a flat, 
two-dimensional grid within a three-dimensional space. It gives access of each BlockVector to other Classes.
When done like this, the iterator hides the internal structure of the data, meaning that you can run through
the BlockVectors without needing to know the implementation details of the Polygonal2DRegion Class. 


Singleton pattern

Code snippet:

![Captura de Ecrã (42)](https://github.com/user-attachments/assets/e967afc9-64aa-4c89-8d8e-832899998fd5)

![Captura de Ecrã (43)](https://github.com/user-attachments/assets/f450c277-140f-47e1-8448-6f23c79be6fe)

Class Diagram:

![Captura de Ecrã (44)](https://github.com/user-attachments/assets/9bcd9bf5-70a3-40e1-9391-1110545440fa)

Exact location: worldedit-bukkit/src/main/java/com/sk89q/worldedit/bukkit/WorldEditListener.java

Discussion of the rationale for identifying: As it is a Singleton pattern, this code makes sure only
one instance of WorldEditPlugin is created. This brings a variety of good things to the code. Most
important of all, it creates a simple and consistent way to access this part of the overall system.
