Long parameter list

Code snippet:

![image](https://github.com/user-attachments/assets/dcfc0681-9819-4d34-9bf2-56fc3a4fa71c)


Exact location: WorldEditPrivate/worldedit-core/src/main/java/com/sk89q/worldedit
/EditSession.java

Explanation of the rationale for identifying this code smell: This can make it harder to understand, maintain, and test the code. The parameters also contain related data (like zero and unit vectors, which are used to configure the environment, and parameters that define the shape and behavior like pattern, expression, hollow, and timeout).

Refactoring proposal:  Group parameters like expression, pattern, and environment as ExpressionConfig. This keeps related functional parameters encapsulated and reduces the direct parameter list for makeShape.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Primitive obsession

Code snippet:

![image](https://github.com/user-attachments/assets/4aa86486-c10d-4b9f-b198-3981d459c88d)
![image](https://github.com/user-attachments/assets/98a38608-e1cc-495d-966e-be24e95d6680)
![image](https://github.com/user-attachments/assets/6b403cb3-68a9-41b3-902e-a4352fc0d91e)




Exact location: WorldEditPrivate/worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java

Explanation of the rationale for identifying this code smell: The code here demonstrates a form of primitive obsession because it's relying heavily on individual x, y, and z values to represent points, rather than using a dedicated class to encapsulate this data. This leads to repeated code and potential readability issues, as calculations are performed on individual coordinates instead of through a well-defined structure.

Refactoring proposal: Create a Point3D class that stores x, y, and z as fields.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

Long Method

Code snippet:

![image](https://github.com/user-attachments/assets/91379cae-deaa-42d9-bd0a-ada934bf372d)

![image](https://github.com/user-attachments/assets/3e917a0c-155c-4d79-b0b6-6c04b1c3bdd9)

Exact location: WorldEditPrivate/worldedit-core/src/main/java/com/sk89q/worldedit/command/RegionCommands.java

Explanation of the rationale for identifying this code smell: Long method, code readability could be improved by breaking this into smaller, focused methods.

Refactoring proposal: Simplify this logic into a separate method.

