Use case name: Super Pickaxe Area / Use case description: The player executes the 
command: "/superpickaxe area <range>". This enables the Super Pickaxe area mode giving the
value "range" as the range of the area pickaxe. / Main actor: Player Secondary actor: None

Use case name: Brush Sphere / Use case description: The player executes the 
command: "/brush sphere [-h] <pattern> [radius]". This applys an effect to an item where it
creates a sphere hollow or filled (indicated by [-h]), of a certain type of block (<pattern>)
with a certain radius ([radius]) / Main actor: Player Secondary actor: None

Use case name: Set Biome / Use case description: The player executes the 
command: "//setbiome [-p] <target>". This changes the Biome to the one specified in <target>.
The [-p] is an optional argument that indicates the position of the biome to be changed. If
this argument is not specified, it changes the biome the Player is currently in.
/ Main actor: Player Secondary actor: None

Use case name: Count Blocks / Use case description: The player executes the 
command: "//count <mask>". This counts the number of blocks that match the <mask>, the <mask>
being a type of block. / Main actor: Player Secondary actor: None

Use case name: Expand Selected / Use case description: The player executes the 
command: "//expand <vert|<amount> [reverseAmount] [direction]". This expands the selected area by an
amount (<amount>), where the [reverseAmout] is the amount to expand the selection by in the other direction
and [direction] is the direction where you want to expand. You can also <vert> instead of the <amount> to
expand the selected are vertically until it hits the sky limit.
/ Main actor: Player Secondary actor: None

Use Case Diagram:
![Captura de Ecrã (49)](https://github.com/user-attachments/assets/1389666e-520e-4e92-8eee-4be040c310b7)
