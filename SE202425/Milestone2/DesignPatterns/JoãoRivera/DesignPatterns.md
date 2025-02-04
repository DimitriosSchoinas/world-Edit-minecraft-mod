Singleton structure

Code snippet:
![image](https://github.com/user-attachments/assets/7ee21ab2-3c4f-48b9-9285-66475846cf8e)

Class diagram:
![image](https://github.com/user-attachments/assets/3649acec-e2b8-4963-ad4c-a08eabb14f12)

Location on the codebase: worldedit-core/src/main/java/com/sk89q/worldedit/WorldEdit.java    method -> getInstance()

Discussion of the rationale for identifying: I initially thought it would make sense to have only a single instance of the mod running so I went to check if that was in fact what happened and it turned out it was. To identify the pattern i searched for a get instance method and made sure the instance itself was made private so that other classes would never get access to it without the getInstance method.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Template Method

Code snippet:
![image](https://github.com/user-attachments/assets/fd944ced-727b-404c-b21e-945d1cf7f29e)
![image](https://github.com/user-attachments/assets/2b4c78c8-dc22-4988-98f6-d82392751f69)

Class diagram:
![image](https://github.com/user-attachments/assets/f5d599c7-5094-4c89-8348-ba93bb104814)

Location on the codebase: worldedit-core/src/main/java/com/sk89q/worldedit/command/argument/DirectionVectorConverter.java    -> DirectionVectorConverter class
                          worldedit-core/src/main/java/com/sk89q/worldedit/command/argument/DirectionConverter.java          -> DirectionConverter class
                          worldedit-core/src/main/java/com/sk89q/worldedit/command/argument/AbstractDirectionConverter.java  -> AbstractDirectionConverter class
                        
Discussion of the rationale for identifying: The 2 classes that extend the abstract class have the exact same methods just a bit different to fit their needs, very similar functionalities and very similar order of operations, so it seemed to be a case of the Template Method being used.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Proxy Pattern

Code snippet:
![image](https://github.com/user-attachments/assets/4a3a6226-b33f-44b7-862d-003496b0c11d)
![image](https://github.com/user-attachments/assets/ef6427a4-8533-4a70-9bd4-ce97c52d3a26)

Class diagram:
![image](https://github.com/user-attachments/assets/1b3427f6-e182-48f8-8009-a83bb0568c93)

Location on the codebase: worldedit-core/src/main/java/com/sk89q/worldedit/extension/platform/PlayerProxy.java          -> PlayerProxy class
                          worldedit-core/src/main/java/com/sk89q/worldedit/extension/platform/AbstractPlayerActor.java  -> AbstractPlayerActor class
                          worldedit-core/src/main/java/com/sk89q/worldedit/entity/Player.java                           -> Player interface

Discussion of the rationale for identifying: Since the class name was what it was i checked it and realized every method in the player proxy was using another method also in the abstractplayeractor, so it seemed to me to be a proxy pattern
