Metric set: Lorenz Kidd Metrics Set

Metrics file: [MetricsES.xlsx](https://github.com/user-attachments/files/17737886/MetricsES.xlsx)

What is represents each Metric:
  1) NOAM -> Number Of Added Methods
             1.2) Added Methods: Nem methods that a subclass introduces, which are not inherited or overridden from a superclass.
  2) NOA  -> Number Of Attributes
  3) NOOM -> Number Of Overridden Methods
             3.1) Overridden Method: A Method in a subclass that has the same name, return type, and parameters as a method in it's superclass. 
  4) NOO  -> Number Of Operations

Analysis of the data:

  NOAM:
    ![Captura de Ecrã (45)](https://github.com/user-attachments/assets/8bc961a3-cb58-478f-9983-703605b77620)
    As said, this metric represents the number of Added Methods (1.2). As we can see, is very common for a Method to have zero or one Added Method.
    This is fine and can be considered good. Most of this methods are specialized for certain purposes and only used in methods
    inside the Class itself. 
    That said, to many can be a sign for a Code Smell. Can indicate that the methods are divided in to many parts, the class is to complex
    or that certain methods should be inherited or overridden from a superclass. From the graph we can conclude that arround 70 to 80
    classes show at least a sign of this problem.
    
  NOA:
    ![Captura de Ecrã (47)](https://github.com/user-attachments/assets/84d856a6-a27c-4cc9-9bfc-17bdf657b7c8)
    This metric counts the number of attributes of a given Class. We can see that this graph has two tendencies, one
    to values more low, which is good and expected, and other close to 17, this may be a problem. We want to
    always have the least amount of attributes as possible, only the ones we need. That said, 17 and 18 is a
    lot. This may indicate that a large group of classes stores to much data. Maybe it would be better to
    divide this classes to minimize class responsability and organize the code.
    
  NOOM:
    ![Captura de Ecrã (46)](https://github.com/user-attachments/assets/b91c3c1c-3980-4154-aa30-4016b4fa60e8)
    NOOM counts the number of overridden methods (3.1) in a class. This can be beneficial. You can define a generic behavior
    in a superclass and then override it in subclasses for more specific behaviors. This makes the codebase more reusable by 
    sharing common logic among related classes while customizing where needed. Even than, this can cause problems if over used.
    Overriding methods in deep inheritance hierarchies can make code complex and harder to understand, as behaviors might be 
    defined or modified in multiple places. As shown in the graph, the results seem pretty good, with only a few having more than
    seven.
    
  NOO:
    ![Captura de Ecrã (48)](https://github.com/user-attachments/assets/719065ba-960f-4e89-9eaf-ebd043474efd)
    Like shown above, this metric counts the number of operations on a given Class. A high number of operations, like in most
    cases, can be needed, but we want as little operations as possible. High number of operations indicates a complex and
    hard code to understand. It can also be a sign that a class takes on to many responsabilities. In this topic, although it
    could be justified if analysed, I consider that there are arroud 150 in a considerable risk of having Code Smells, with arroud
    15 classes in a big risk of having the same.
    
Potential trouble spots:
  This were the most problematic classes in each metric:
    NOAM: com.sk89q.worldedit.internal.util.InfoEntryPoint.NavigableEditorPane
    NOA: com.sk89q.worldedit.world.item.ItemTypes
    NOOM: com.sk89q.worldedit.internal.expression.invoke.CompilingVisitor
    NOO: com.sk89q.worldedit.bukkit.adapter.impl.v1_21.PaperweightFakePlayer
