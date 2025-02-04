Primitive Obsession

Code snippet:
![image](https://github.com/user-attachments/assets/ca220323-31af-4f9e-9ffc-438ecbdffaf2)
![image](https://github.com/user-attachments/assets/9cd6ddff-6222-446c-a08c-485d6748d9c9)
![image](https://github.com/user-attachments/assets/fc653165-6aab-4969-9f9c-c6845326fefd)

Exact Location: worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java -> method in question -> makeCylinder

Explanation of the rationale for identifying this code smell: Was checking through the method and found a bunch of int and double usage when we are talking about coordinates.

Refactoring proposal: Create classes to store these values as points in space/plane instead of just using ints and doubles.

Note: This code smell extends to most of the makeX methods in this same class
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Data Clump

Code snippet:
![image](https://github.com/user-attachments/assets/cea5355f-9d7c-4169-9ef6-a0feb5a3212a)
![image](https://github.com/user-attachments/assets/c23ed091-ae3f-4d71-8328-fac4eef20fca)
![image](https://github.com/user-attachments/assets/bd2b0587-f501-4ca2-ab89-a4a869faf4f3)
![image](https://github.com/user-attachments/assets/94bc3440-fd72-43a9-96b7-a65420a99d26)

Exact Location: worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java -> methods in question -> deformRegion (theres like 3 of them), makeShape(also like 3 of them)

Explanation of the rationale for identifying this code smell: Was scrolling through this class after finding the previous code smell and found around 6 methods in a row using the same paramaters.

Refactoring proposal: Make this collection of parameters into a class of its own, being careful so as to not create a data class.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Data Class

Code snippet:
![image](https://github.com/user-attachments/assets/3ef2aa5b-d45a-4f4b-8ba9-9b045ecde7dd)

Exact Location: worldedit-core/src/main/java/com/sk89q/worldedit/util/task/AbstractTask.java -> AbstractTask class

Explanation of rationale for identifying this code smell: Opened the class and realised there were only get methods implemented.

Refactoring proposal: This class is only extended by one other class so just put into that other class these get methods.

