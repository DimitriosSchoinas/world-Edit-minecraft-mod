Factory Pattern

Code snipet: ![image](https://github.com/user-attachments/assets/d2bf6cdf-2ef1-4a03-ba78-db4e35c0baa5)

Class diagram: ![image](https://github.com/user-attachments/assets/7869ac8f-147f-4e9b-a805-f3dfc9832f97)

Location on code base: WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit

Discussion of the rationale for identifying: The importance of this Factory Pattern lies in providing a more robust, flexible and easy-to-maintain design. Tt facilitates consistent EditSession creation by centralizing the logic, making the system more adaptable to change, and improving interoperability between different parts of the code.

----------------------------------------------------------------------------------------------------------

Template Method Pattern

Code snipet: ![image](https://github.com/user-attachments/assets/16df7857-7bf8-457d-9dd1-6ab170db7af9)

Class diagram: ![image](https://github.com/user-attachments/assets/6dbab472-6290-456d-95d7-6dcb665df522)

Location on code base: WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\worldedit\registry\state

Discussion of the rationale for identifying: Using the Template Method in AbstractProperty is crucial to ensure consistency, common code reusability, and extensibility when dealing with different types of properties. This results in a more robust, scalable, and maintainable design, allowing new types of properties to be easily added to the system without breaking or duplicating existing logic.

-----------------------------------------------------------------------------------------------------------

Observer Pattern

Code snipet: ![image](https://github.com/user-attachments/assets/7c37ec0c-c10d-4af8-a1f0-97163db4344b)

Class diagram: ![image](https://github.com/user-attachments/assets/3db08467-9f5d-48c0-bbb5-5ea98c7d0280)

Location on code base: WorldEditPrivate\worldedit-core\src\main\java\com\sk89q\util\eventbus

Discussion of the rationale for identifying: Implementing the Observer Pattern with MethodHandleEventHandler is crucial for a robust, decoupled, and efficient event system. It provides greater flexibility, scalability, and reliability, ensuring that different parts of the system can interact in a fluid and modular manner, even in large-scale projects.
