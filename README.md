# JavaSimpleIRC
JavaSimpleIRC is a simple IRC (Internet Relay Chat) program, which allows communication via sockets. After the administrator sets up the server and channels, users can connect to it. Users can then exchange messages or participate in groups talks.

## Project structure
Project is divided into 3 Maven modules.

### Util
Serves as a bridge between the 'client' and the 'server' module. It contains useful methods for manipulating with strings, packets, connections and files.
    
### Server
Server is a standalone console based program, that can send responses and receive requests from the clients. It contains the following built-in commands for easier IRC server management:  
| Command      | Description                                 |
| ---------------------------------------------------------- |
| commands     | Displays the command list.                  |
| exit         | Terminates the program.                     |
| info         | Displays the info of a command.             |
| clients      | Displays connected clients' data.           |
| kick         | Kicks an user.                              |
| say          | Sends a message to other clients.           |
| motd         | Displays/sets the motd.                     |
| channel      | Channel manager.                            |
| channelsay   | Sends a message to all channel's clients.   |

### Client
Is also a standalone GUI based program, that aims for simplicity and ease of use. Clients can connect to servers on localhost or outer web.
![Client login](https://i.imgur.com/a4JlBQ7.png)
![Client main](https://i.imgur.com/geSYkLt.png)