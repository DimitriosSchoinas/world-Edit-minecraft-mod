Metrics set used: Lanza-Marinescu metrics set

Metrics from the metrics set: -Access To Foreign Data (ATFD)
                              -Number Of Public Attributes(NOPA)
                              -Number Of Accessor Methods(NOAC)
Graph:
![image](https://github.com/user-attachments/assets/4abbc9f8-0459-4ca5-81bd-afa26cc59a14)

File with the values used to make the graph:
[WorldEditPrivate.xlsx](https://github.com/user-attachments/files/17693307/WorldEditPrivate.xlsx)

ATFD metric: This metric measures the amount of attributes from other classes directly accessed by the class being measured. A high ATFD value can indicate high coupling between classes, a low ATFD value is desirable as it implies better encapsulation and modularity. 3 of the classes that show an extreme ATFD value is the DefaultBlockParser class(worldedit-core/src/main/java/com/sk89q/worldedit/extension/factory/parser/DefaultBlockParser.java), the UtilityCommands class(worldedit-core/src/main/java/com/sk89q/worldedit/command/UtilityCommands.java) and the BrushCommands class (worldedit-core/src/main/java/com/sk89q/worldedit/command/BrushCommands.java). A high value of this metric is also usually associated to the feature envy code smell.

NOPA metric: This metric measures the number of attributes within a class that are publicly accessible. Public attributes are those that can be accessed or modified directly by other classes, without the need for any access methods (getters/setters). A high value of the NOPA metric can indicate bad encapsulation and bring unintended side effects or coupling between classes, which makes the system harder to extend. Also good design principles suggest using private attributes and using setter/getter methods to access these attributes. The BlockMap class (worldedit-core/src/main/java/com/sk89q/worldedit/util/collection/BlockMap.java) shows an extreme level of NOPA. A high value of this metric is also usually associated to the data clump and god class code smells.

NOAC metric: This metric refers to the number of getter and setter methods (accessors) that a class provides to access or modify its private or protected attributes. These methods are typically used to access the attributes of a class indirectly, promoting encapsulation and data hiding. A high NOAC value could signal that said class is doing too much or its internal state is poorly encapsulated and a low could suggest insufficient access to class attributes or incomplete encapsulation. This WorldEdit project has an extremely high amount of extreme NOAC values, with the biggest outliers being by far the EditSession class (worldedit-core/src/main/java/com/sk89q/worldedit/EditSession.java). High values of this metric is usually associate with the excessive getters and setters and god object code smells.
 
