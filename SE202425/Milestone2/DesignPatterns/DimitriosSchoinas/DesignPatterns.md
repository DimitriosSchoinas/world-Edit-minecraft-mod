**Factory method pattern**

Code snipet:
 ![image](https://github.com/user-attachments/assets/7a8c075f-2ba3-4ed2-b5e2-f756a127bf5a)

Class diagram:
 ![image](https://github.com/user-attachments/assets/4a007107-3bd6-4b80-b5e7-ad8afb1c04ac)
 

Location on code base: WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\command\factory\FeatureGeneratorFactory.java    createFromContext() method

Discussion of the rationale for identifying:
The FeatureGeneratorFactory class uses the createFromContext() method to create a new RegionMaskingFilter instance.
This method accepts an EditContext as a parameter, which gives you access to the context needed to construct a RegionMaskingFilter object configured with a noise filter and a feature generator .
Instead of directly creating these objects in various parts of the code, the FeatureGeneratorFactory factory encapsulates the creation and allows client code to request configured instances without worrying about the internal details.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Adapter pattern**

Code snipet:
![image](https://github.com/user-attachments/assets/ed153dd5-3707-4bb5-8d58-1e1dbdb5a402)

Class diagram:

![WhatsApp Image 2024-11-03 at 16 44 34](https://github.com/user-attachments/assets/8c9fdf3e-7ce5-40b2-a24e-af0560a24d0b)

Location on code base: WorldEditPrivate\worldedit-fabric\src\main\java\com\sk89q\worldedit\fabric\FabricAdapter.java  (for example: public static Block adapt(BlockType blockType) )

Discussion of the rationale for identifying:
The Adapter pattern was chosen because it provides a structured and efficient way to convert data between two incompatible systems (Minecraft and WorldEdit) without altering either system. This approach promotes code reuse, maintainability, and scalability while keeping the interfaces clean and compatible.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Iterator Pattern**

Code snipet:

![image](https://github.com/user-attachments/assets/3349d0a4-c224-4e63-a754-d65a2e4050ce)
![image](https://github.com/user-attachments/assets/2a70fb62-2e84-49f8-bf2d-87270f53f519)

Class diagram:

![image](https://github.com/user-attachments/assets/94c8f6ed-5573-434a-98bf-1cd71d17fba5)

Location on code base:
WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\regions\CylinderRegion.java    (iterator() method)
and WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\regions\iterator\FlatRegion3DIterator.java

Discussion of the rationale for identifying:
In the context of CylinderRegion, this pattern is used to enable traversal of the blocks within a cylindrical region. By providing an iterator, the class lets other parts of the WorldEdit code to process each block in the region without needing to understand how the region is represented internally or how to calculate the points within the cylinder.
