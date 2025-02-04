Metric set: Chidamber-Kemerer Metrics Set

Metrics file:
[worldedit2.xlsx](https://github.com/user-attachments/files/17692374/worldedit2.xlsx)

Report:

Weighted Methods per Class (WMC):
Measures the sum of complexities for all methods in a class. Higher WMC values indicate more complex classes with numerous or complex methods. 
High WMC suggests that a class is doing too much, which may lead to difficulties in understanding and maintaining the class. Classes with lower WMC are generally simpler and more focused.
High WMC values can suggest potential code smells such as Divergent Class (a class that has too many responsibilities) and Long Method. Reducing WMC through method extraction or refactoring could enhance readability and maintainability.

Depth of Inheritance Tree (DIT):
Represents the maximum inheritance depth of a class within the hierarchy. Higher values mean a class is further down in the inheritance hierarchy.
Higher DIT values indicate more inheritance and potential for reuse, but may also introduce greater complexity.
A deep inheritance tree (high DIT) can make code more challenging to understand and maintain. A potential code smell is for example Refused Bequest (when subclasses do not use inherited methods). Monitoring DIT helps to ensure inheritance is used appropriately, enhancing reusability without adding unnecessary complexity.

Number of Children (NOC):
Counts the direct subclasses a class has.
High NOC indicates that a class is a parent to many subclasses, which can suggest it is widely reused or generalized.
A high NOC can sometimes point to Speculative Generality (overly generalized classes created with future use cases in mind) and potential Divergent class aspects (excessive responsibility in a single class). Classes with fewer children are often more focused, while classes with numerous children should be reviewed for appropriate use of inheritance versus composition.

Coupling Between Object Classes (CBO):
Measures the number of classes that a class is coupled with. High CBO indicates strong dependencies between classes.
Higher coupling makes code harder to change because modifications in one class can affect many others.
High CBO is often associated with Feature Envy (a class that overly relies on data or functionality in other classes) and Inappropriate Intimacy (classes too familiar with each other’s details). Reducing CBO through refactoring or introducing interfaces can improve modularity and make maintenance easier.

Response for a Class (RFC):
Counts the methods that can be invoked in response to a message sent to a class, including both its own methods and methods of other classes it calls.
High RFC values indicate that a class has a large “surface area” of functionality, making it potentially more complex to understand and test.
High RFC values can suggest a Divergent Class (if the class has many methods and interacts heavily with others) or Feature Envy (if it relies on methods from other classes excessively). Monitoring RFC can guide simplifying class interactions to improve readability and testability.

Lack of Cohesion in Methods (LCOM):
Measures the cohesiveness of methods within a class. Higher values indicate lower cohesion, meaning methods within a class are less likely to use the same fields or methods.
High LCOM suggests that a class may be handling unrelated responsibilities and is a candidate for refactoring.
High LCOM values are indicators of Large Class . Improving cohesion by breaking up large classes can help align with the Single Responsibility Principle, simplifying maintenance.

Potential trouble spots in the codebase: 

![image](https://github.com/user-attachments/assets/0f99dea8-745d-46a0-972e-72c1709dc550)

Based on the collected metrics, I identified potential trouble spots in the codebase by examining extreme values through the graphics and metrics values collect. These visualizations allowed me to see the distribution of each metric and pinpoint outliers.
For example, classes like EditSession and FabricRegistries had significantly high WMC and RFC values, suggesting they may be Divergent Classes with too many responsibilities.
Classes such as BlockVector3 had high CBO values, indicating a strong dependency on other classes, which could make it difficult to modify without affecting other parts of the system.
Classes with high LCOM values, like OffsetsMask2D.Builder, show low cohesion and may be handling multiple responsibilities, suggesting they may benefit from refactoring to improve cohesion and follow the Single Responsibility Principle. Addressing these trouble spots could enhance code readability, maintainability, and reduce potential bugs.

By examining the metrics in conjunction with identified code smells, we can see clear relationships:
The high CBO (Coupling Between Object Classes)  and low cohesion (High LCOM) values point to potential Inappropriate Intimacy by indicating strong interdependencies and scattered responsibilities.
The high WMC(Weighted Methods per Class) and high RFC(Response for a Class) suggest Long Method issues, as they reflect classes with complex methods that could be simplified .Also I want to say that the graph shows that there might be a lot of code smells related to the CBO metric since it’s extreme bar is very high compared to the others. 

Relation with the identified code smells:
I found the Inaproppriate Intimacy code smell because of the collected code metrics, the metric CBO(Coupling Between Objects ) was way higher in the class ToolCommands compared to the average number between all classes.
I found the Long method code smell with the collected code metrics because the value of the WMC( Weighted Method Count ) metric was almost double the avarage class value, so for me it was a clue that it could be a code smell.

