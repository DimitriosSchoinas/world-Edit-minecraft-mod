**User Storys**

User story 1 

Reviewd by: Diogo Mateus e Dimitrios Schoinas- We think that this command already exists, however , if you extend it by implementing new features like a favourites system, it will be a handsome idea.

User story 2

Reviewd by: João Rivera e Gonçalo Cascais - For us the idea seems very appealing and interesting.

User story 3

Reviewd by:João Nascimento - It's a very good idea to implement this command.

**Code metrics Reviews**

Dimitrios code metrics- Review from Gonçalo Cascais 60046: The metrics are well defined and easy to understand, good work.

Diogo code metrics-Review by João Nascimento 62896 -> Seems nice and I liked the explanation, identifying where could be a code smell, eventhough not identifying which ones.

Goncalo code metrics-Review by João Rivera 62877 -> Looks good to me, well explained and associated to extreme cases of each class, just seems like you could've also added which code smells relates to each metric

Joao Nascimento code metrics- Review by Diogo Mateus 65379: It's explained what each metric does and how it indicate which Code Smells, and I like that. You didn't make a connection with the database and the code. The excel sheet having more information than needed also doesn't help.

Joao Rivera code metrics-Review from Dimitrios Schoinas 65313: I think that your metrics graph is very well done since it makes easier to identify potencial code smells; however, I can't visualise well your metrics excel document possibly due to incopatibilitys


**Code smells Reviews:**

Dimitrios Code Smells

Review code smell 1: João Rivera 62877 -Hard to find how you would proceed with the refactoring since it is in the middle of the explanation of how you found this code smell.

Review code smell 2: Gonçalo Cascais 60046 -Your analysis of the code smell is well done, You've accurately identified the potential issues and provided clear explanations on why they could impact maintainability and readability.

Review code smell3: João Nascimento 62896 -The code smell is well explained and is a correct identification of a long parameter code smell. The solution apresented should solve the problem in question. Well done.

Diogo Code smells

Review code smell 1: João Nascimento 62896 - It is true, this Long Parameter code smell could lead to a very difficult understanding of the code.

Review code smell 2: Dimitrios Schoinas 65313 - This particular snippet shows multiple method calls across different classes, resulting in a complex dependency chain. So I think it is well defined

Review code smell 3: Gonçalo Cascais 60046 - I believe that making this adjustment will enhance the clarity of the code and simplify future updates or modifications

Goncalo code smells

Review Code Smells 1: João Rivera 62877 - Looks good to me, the suggestion for the refactoring also seems correct and how I would do it.

Review Code Smell 2: Diogo Mateus 65379 - I agree with the found problem. The implementation of that Class should solve the issue in question. Good work.

Review Code Smell 3: João Nascimento 62896 - Agreed, it is a too long method impacting the understanding of the code, good job identifying it!

Joao Nascimento code smells

Review code smell 1: João Rivera 62877 - Looks good only shares the same problem I had with the other code smell I reviewed that is the refactoring is hard to find as it is in the middle of the explanation of how you found the code smell.

Review code smell 2: Dimitrios Schoinas 65313 - This code smell seems well identified since there is not any code inside it , which means that this method is a good example for speculative generality code smell since it doesn't add any behavior and adds unnecessary 
complexity to the code.
Review code smell 3: Diogo Mateus 65379 - Looks all good to me. Well identified and for sure a Long Method code smell.

Joao Rivera code smells

Review Code Smell 1: Diogo Mateus 65379 - More examples than needed which helps identify and visualize the problems better, good work. You also divided the main points well. The only thing to note is that in the Code Snippet, it would be easier if you showed in which line the method was located, as it would make it easier to find in the Class.

Reveiew Code Smell 2: Dimitrios Schoinas 65313 -I think that overall you selected a good code smell since I can easily see a better implementation as you said.

Reveiew Code Smell 3: Gonçalo Cascais 60046 - I completely agree with your suggestion to create an object for the parameters instead of passing multiple individual parameters. This approach will definitely enhance the clarity and maintainability of our code.


**Design Patterns:**

Dimitrios Patterns

Design pattern 1: Diogo Mateus 65379- Well explained and can't find any errors in the Class Diagram. 5 stars!

Design pattern 2: João Nascimento 62896 - Solid and well explained!

Design Pattern 3: Gonçalo Cascais 60046 - Well defined and clearly implemented

Pattern 1 - Dimitrios Schoinas 65313 - I think that the class is well identified even though you could show a better method as an example

Pattern 2 - João Rivera 62877 - Looks like an iterator pattern and seems to me that the class diagram is also well done.

Patter 3 - Gonçalo Cascais 60046 - Singleton well identified

Goncalo Patterns

Pattern 1 - João Rivera 62877 - Everyithing looks good on this one.

Pattern 2 - João Nasicmento 62896 - Is a good identification of the pattern. Explains the necessity of it well. Good work!

Pattern 3 - Dimitrios Schoinas - I think it is well explained since the class has just 1 entry and 1 access point to that instance

Joao Nascimento Patterns

Pattern 1 - João Rivera 62877 - Seems like a factory pattern to me well done.

Pattern 2 - Gonçalo Cascais 60046 - The template pattern is well identified.

Pattern 3 - Diogo Mateus 65379 - Looks well done, I agree that it's an observer Design Pattern.

Joao Rivera Patterns

Pattern 1 - Dimitrios Schoinas 65313- I think that it is a very good example of the singleton design pattern

Pattern 2 - João Nascimento 62896 - Agreed and well identified!

Pattern 3 - Diogo Mateus 65379- You think well, that's a proxy indeed. Well done.

**Use cases Reviews**:

Dimitrios Use cases

Review from João Rivera 62877: I feel like the saying the player binds the tool is incorrect as it is the system itself that binds it, the player simply selects which tool he wishes to be bound.

Diogo Use cases

Review from Gonçalo Cascais 60046: Everything seems fine, all use cases are clearly identified

Goncalo use cases

Review from: Dimitrios Schoinas - I think that the line disposition is wrong. The lines should come from different points and not all from the same point.

Joao Nascimento use cases

Review from Diogo Mateus 65379: At the moment of this writing, you have the same Use Case that Rivera did "pos1". That's something you need to change, since he did it first.

Joao Rivera Use cases

Review from João Nascimento 62896 -> A nice and detailed description and a very nice graphic, these are very good use cases, good job!


**Milestone3**

**//weather <argument>**

Use case text made by Dimitrios and Reviewed by Diogo: You say that "There is a selected region" is a pre condition but after you have
an altenative flow which says "No Region Selected". If you have that as a pre condition that alternative flow can't happen because you are assuming
that you always have a selected region. EDIT: After the change I agree with all work done

Use case diagram made by Diogo and Reviewed by Dimitrios: I'm not 100% sure if the diagram is correct but overall seems ok.

Class Diagram made by Dimitrios and Reviewed by Diogo: I can't find any issue with your Class Diagram. Good work!

Sequence diagram made by Dimitrios and Reviewed by Diogo: I did a verification and I agree with your implementation.

Scenario Based Testing( Drought/Snow) made by Dimitrios and Reviewed by Diogo: I see no problems, all seems good.

Scenario Based Testing( hell/rain)  made by Diogo and Reviewed by Dimitrios: I think that your scenario test base is well implemented

Tour report made by Diogo and Reviewed by Dimitrios: This tour report is correct and complete.

**//lake <radius> <type>**

Use case text made by João Rivera and Reviewed by João Nascimento: that's right! 

Use case diagram made by João Rivera and Reviewed by João Nascimento: Simple but that's literally it, simple but effective!

Class Diagram made by João Rivera and Reviewed by João Nascimento: It's funny how the generate commands class is at the center and everything else connects to there, nice job!

Sequence diagram made by João Rivera and Reviewed by João Nascimento: I see no problems in it!

Scenario Based Testing made by Gonçalo Cascais and Reviewed by João Rivera: It all looks good to me.

Code for the base and water type made by Gonçalo Cascais and Reviewed by João Rivera: The code looked good, the only thing that was wrong was the way the radius was used as an argument, later corrected to be a double instead of a list

Code for the lava, pond, oasis, infinite types made by João Rivera and Reviewed by João Nascimento:

Tour report made by João Rivera and Reviewed by João Nascimento: This is all correct and complete.

**//schem favorite <schematic_name>**

Use case text made by João Nascimento and Reviewed by João Rivera:

Use case diagram made by João Nascimento and Reviewed by Gonçalo Cascais: Looks correct

Class Diagram made by João Nascimento and Reviewed by Gonçalo Cascais: Looks correct

Sequence Diagram made by João Nascimento and Reviewed by Gonçalo Cascais: Looks correct

Scenario Based Testing made by João Nascimento and Reviewed by Gonçalo Cascais: Everything looks fine.

Tour Report made by João Nascimento and Reviewed by Gonçalo Cascais: All correct.

**//schem search <search_term>**

Use case text made by João Nascimento and Reviewed by Gonçalo Cascais: Well done

Use case diagram made by João Nascimento and Reviewed by Gonçalo Cascais: Well done

Class Diagram made by João Nascimento and Reviewed by Gonçalo Cascais: Seems ok

Sequence Diagram made by João Nascimento and Reviewed by Gonçalo Cascais: Looks correct

Scenario Based Testing made by João Nascimento and Reviewed by Gonçalo Cascais: Everything looks fine.

Tour Report made by João Nascimento and Reviewed by Gonçalo Cascais: All correct.
