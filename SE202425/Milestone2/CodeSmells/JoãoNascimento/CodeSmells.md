Long Parameter List

Code snipet:

![image](https://github.com/user-attachments/assets/a2abfceb-8dbf-4e5b-9bfa-fd8ea955afaf)

Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/util/TargetBlock.java

Explanation of the rationale for identifying this code smell: The use of a long parameter list makes it harder to understand, maintain, and test the code. A large number of parameters, especially when they are related, can obscure the function's intent and increase the cognitive load required to understand it. In this case, the parameters seem to contain both data that configures the environment (e.g., location vectors) and data that controls behavior (e.g., distance, rotation, view height).

-------------------------------------------------------------------------------

Speculative Generality

Code snipet:

![image](https://github.com/user-attachments/assets/15eb3851-aa1b-4665-af4f-6dc6d49854e7)

Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/MaxBrushRadiusException.java

Explanation of the rationale for identifying this code smell: This subclass is completely empty, just extends MaxRadiusException class but doesn't add any behavior. This adds unnecessary complexity to the code.

-------------------------------------------------------------------------------

Long Method

Code snipet:

![image](https://github.com/user-attachments/assets/a80fd592-792c-4e79-9f54-491856a4f5d0)
![image](https://github.com/user-attachments/assets/2b07621f-a8db-44bc-b08c-750784c5fe12)
![image](https://github.com/user-attachments/assets/6bfc72eb-abc2-4a97-b4a8-ec505a975db4)

Exact location: worldedit-core/src/main/java/com/sk89q/worldedit/WorldEdit.java

Explanation of the rationale for identifying this code smell: This method is too long and may be hard to maintain, hard to test, and most importantly, hard to read. Refactoring this into smaller methods could help with that for the future.
