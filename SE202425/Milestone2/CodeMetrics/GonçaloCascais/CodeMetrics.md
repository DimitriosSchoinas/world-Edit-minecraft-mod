
Metrics set used: Li-Henry metrics set

Metrics from the metrics set: -Number of attributes and methods (size2) -Data abstraction Coupling(DAC) -Number of Methods(NOM) - Message of methods(MPC)

File with the values used to make the graph:[WorldEditMetrics.csv](https://github.com/user-attachments/files/17716239/WorldEditMetrics.csv)


DAC Metric: A high DAC (Data Abstraction Coupling) value indicates that the class has many dependencies on other classes, meaning it uses many data types that belong to other classes. This implies that the class is heavily coupled with other parts of the system. Such high coupling can have negative implications for the maintainability, flexibility, and reusability of the class. A class with extreme values of DAC is LocalSession(worldedit-core/src/main/java/com.sk89q/worldedit/LocalSession)

MPC Metric: A high MPC (Message Passing Coupling) value indicates that a class makes numerous method calls to other classes. In other words, it frequently relies on methods from other objects to accomplish its tasks, leading to a high level of interaction or coupling between the class and other classes. We have extreme values of MPC in class ItemTypes(worldedit-core/src/main/java/com.sk89q/worldedit/world/item/ItemTypes

 SIZE2 Metric: A high SIZE2 metric value indicates a large number of methods within a class. In object-oriented design metrics, SIZE2 specifically measures the total count of methods defined in a class, both inherited and newly created. When SIZE2 is high, it points to a class with many responsibilities or behaviors, which can have several implications. Extreme values of this metric in PaperweightFakePlayer(worldedit-bukkit/adapters(adapter-1.21/sec/main/java/com.sk89q.worldedit.bukkit.adapter.impl.v1_21/PaperweightFakePlayer)

NOM Metric: A high NOM (Number of Methods) value indicates that a class has a large number of methods. This metric is often used to assess the complexity and responsibility of a class in object-oriented programming. When the NOM is high, it suggests that the class is likely managing multiple tasks or contains significant functionality. Extreme values of NOM in class NavigableEditorPane(com.sk89q.worldedit.internal.util.InfoEntryPoint.NavigableEditorPane)
