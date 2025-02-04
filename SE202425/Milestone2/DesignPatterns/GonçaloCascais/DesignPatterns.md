Adapter pattern

Code snippet:

![image](https://github.com/user-attachments/assets/8c6dbdd9-b65a-4225-a005-bf686bf079eb)

Class diagram:

![image](https://github.com/user-attachments/assets/7b881b8f-4df8-4f7f-897a-cdd71ad2c496)

Exact location: WorldEditPrivate/worldedit-fabric/src/main/java/com/sk89q/worldedit/fabric/FabricAdapter.java

Discussion of the rationale for identifying: The FabricAdapter, as a Adapter pattern, helps the communication between two incompatible systems. In this case the systems are the minecraft and the WorldEdit.
It receives a World object (from the WorldEdit API) and checks if it is an instance of FabricWorld. If it is, this means that the World is already adapted for WorldEdit, and the method retrieves the original Level contained within FabricWorld.

----------------------------------------------------------------------------------------------------
Template Pattern

Code snippet:

![image](https://github.com/user-attachments/assets/caddd645-56ee-4f6c-874e-bd34b4797c82)
![image](https://github.com/user-attachments/assets/87cdc318-b70c-4d76-946c-934c8b1e33f5)


Class diagram:

![image](https://github.com/user-attachments/assets/f1c05160-a26e-4554-aad6-57c90ddede17)


Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/extent/AbstractDelegateExtent

Discussion of the rationale for identifying: Using the Template Method in AbstractDelegateExtent is crucial to ensure consistency, common code reusability, and extensibility when dealing with different types of properties. This results in a more robust, scalable, and maintainable design, allowing new types of properties to be easily added to the system without breaking or duplicating existing logic.


--------------------------------------------------------------------------------------------------------
Singleton pattern 

Code snippet:

![image](https://github.com/user-attachments/assets/e6b8741c-2437-4b47-9809-24b75c1eb4af)

Class diagram:

![image](https://github.com/user-attachments/assets/aeb4cb06-f75f-4f58-b30e-451f35664a98)

Exact location: WorldEditPrivate/worldedit-core/src/main/java/com/sk89q/worldedit/util/time
/FileNameDateTimeParser.java

Discussion of the rationale for identifying: I was looking through the code when I saw a reference to a calendar and thought it would make sense to just have an instance of a calendar. I checked and found the FileNameDateTimeParser class.


