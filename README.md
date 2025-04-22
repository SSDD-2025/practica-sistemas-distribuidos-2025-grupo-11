# Tennis Tour

## Phase 1

## Colaborators
| Name | Email | Github account |
|-----------|-----------|-----------|
| Ignacio Carnal Bernal | i.carnal.2022@alumnos.urjc.es  | [Nachooo51](https://github.com/Nachooo51) |
| Iker Morante Aceituno | i.morante.2022@alumnos.urjc.es | [sgiker21](https://github.com/sgiker21) |

## Execution Instructions
*Note that this project has been developed using Visual Studio Code, so these instructions will be done considering this IDE. Some instructions may differ in other IDEs. 

1. Download the .zip from the repository and unzip it. Open the project in VS Code
2. Download MySQL Workbench. Username is root, password Mysqlroot21. Port is by default 3306
3. Open Workbench and write the following query: 
        CREATE databaseTennis;
        USE databaseTennis;
4. Run application on VS Code
5. After spring boot has initialised the web, go to //localhost:8080 on your browser

## Programs and other software to install
1. Visual Studio Code: https://code.visualstudio.com/download
2. Java: JDK 21 https://www.oracle.com/java/technologies/downloads/#java21 (or install the extension Java)
2. MySQL v.8.0.41: https://dev.mysql.com/downloads/file/?id=536787
3. Maven v.4.0.0: https://maven.apache.org/download.cgi (or install the extension Maven for Java)
4. Spring Boot v.3.4.3: Install the extension Spring Boot Extension Pack in VS Code
## Application Functionality
Tennis Tour is an exciting application where you can create your own team and add players to it. The app allows you to organize multiple matches between teams, providing a dynamic and competitive environment. Additionally, teams can participate in tournaments with many other teams, all striving for a prize and a spot in the team rankings. It's a great way to challenge yourself and compete against others in the world of tennis!

### Website

- **Main page**
![Main page](/images/Navigation%20Diagram/Home.png)
This is the main page of Tennis Tour. Here we can see the upcoming matches that are going to be played. We can also view the top teams currently in the ranking, as well as the upcoming tournaments that will take place.

- **Create a player**
![Create a player](/images/Navigation%20Diagram/player-form.png)
On this page, we will be able to create a player who can later be added to a team.

- **Show all players**
![Show all players](/images/Navigation%20Diagram/Player-List.png)
On this page, we will be able to see a list of all the players who have joined Tennis Tour.

- **Show player stats**
![Show player stats](/images/Navigation%20Diagram/Player.png)
On this page, we will be able to see the stats of a particular player.

- **Create a team**
![Create a team](/images/Navigation%20Diagram/Team-form.png)
On this page, we will be able to create a team by choosing two players who already belong to Tennis Tour, as well as a name for the team.

- **Show all teams**
![Show all teams](/images/Navigation%20Diagram/Team-list.png)
On this page, we will be able to see a ranking of all the teams.

- **Show team stats**
![Show team stats](/images/Navigation%20Diagram/Team.png)
On this page, we will be able to see the stats of a specific team.

- **Create a match**
![Create a match](/images/Navigation%20Diagram/Match-form.png)
On this page, we will be able to create a match by choosing two teams, a date, and a surface on which the match will be played.

- **Show all matches**
![Show all matches](/images/Navigation%20Diagram/Match-list.png)
On this page, we will be able to see a list of matches that will be played in the coming days.

- **Show match details**
![Show match details](/images/Navigation%20Diagram/match.png)
On this page, we will be able to see the details of a particular match.

- **Create a tournament**
![Create a tournament](/images/Navigation%20Diagram/Tournament-form.png)
On this page, we will be able to create a tournament. To do this, we will need to choose a name, the date, the surface on which it will be played, the prize, and the points that will be awarded to the first place. Additionally, we will need to choose four different matches that will participate in the tournament.

- **Show all tournaments**
![Show all tournaments](/images/Navigation%20Diagram/tournament-list.png)
On this page, we can see a list of tournaments that will take place.

- **Show tournament details**
![Show tournament details](/images/Navigation%20Diagram/tournament.png)
On this page, we will be able to see the stats of a particular tournament, as well as its bracket.

- **General error**
![General error](/images/Navigation%20Diagram/error.png)
This is the general error page for anything that goes wrong while navigating the website.

- **Specific error**
![Specific error](/images/Navigation%20Diagram/errorPlayer.png)
This error page specifically appears when someone registers a new player and leaves their first name and/or last name blank, or when there is already another player with the same first and last name.

## Navigation Diagram
- Blue: Accessible for all users
- Orange: Accessible for registered users
- Red: Accessible only for admins
![](/images/Navigation%20Diagram/Navigation_Diagram.png)

## Database Entities Diagram
![](/images/entitiesDiagram.png)

## Classes and Templates Diagram
![](/images/classDiagram.png)

## Team Members Participation

### Ignacio Carnal Bernal
- **Tasks:** Creation and implementation of repositories, services, and controllers for all entities except User. Implementation of various HTML files related to the entities, as well as multiple CSS styles.
- **5 most important commits**
  - [Commit 1](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/commit/22cf99e7e8ee72047a595cddc5e7297edd69f5fb)
  - [Commit 2](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/commit/fd91759611045f36ac442763afeb104c93d3b255)
  - [Commit 3](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/commit/03665882ca1c769ad1c73166297d6e634760897e)
  - [Commit 4](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/commit/17b9d3a6125d51c2c842a47e19d8f51057ee1e67)
  - [Commit 5](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/commit/5e3ed45ca8463a28dda54a74c2029978ae50315d)
- **5 files with the most contributions**
  - [File 1](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/java/ssdd_web/web_project/controller/TournamentController.java)
  - [File 2](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/java/ssdd_web/web_project/controller/TeamController.java)
  - [File 3](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/java/ssdd_web/web_project/services/TournamentService.java)
  - [File 4](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/java/ssdd_web/web_project/services/MatchService.java)
  - [File 5](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/java/ssdd_web/web_project/services/TeamService.java)

### Iker Morante Aceituno
- **Tasks:** Creation of HTML templates, some stylesheets. Management of the web controller, and architecture related with the user. Working with the MySQL Workbench to store data. 

- **5 most important commits**
  - [Commit 1](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/tree/7c3135d86ae478119cd8d36981e38558dd36fb02)
  - [Commit 2](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/tree/853d1c70ebd4f89e480260177b7f009aebae8e0c)
  - [Commit 3](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/tree/fd12c0e25592a4ad60d7bd8b8f2d2c5f00f093a8)
  - [Commit 4](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/tree/fc34cb4717a4d9c57bac80d498a840e7e6ec25ac)
  - [Commit 5](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/tree/232e9b0987961230836e5f936fadf7fc071154b7)
- **5 files with the most contributions**
  - [File 1](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/resources/templates/PlayerRegistration.html)
  - [File 2](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blame/main/web_project/src/main/java/ssdd_web/web_project/services/UserService.java)
  - [File 3](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/resources/templates/error.html)
  - [File 4](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/resources/templates/Player.html)
  - [File 5](https://github.com/SSDD-2025/practica-sistemas-distribuidos-2025-grupo-11/blob/main/web_project/src/main/java/ssdd_web/web_project/controller/WebController.java)


## Phase 2

## Execution Instructions



## Application Functionality

### Website



## Navigation Diagram
- Blue: Accessible for all users
- Orange: Accessible for registered users and admins
- Red: Accessible only for admins
![](/images/Navigation%20Diagram/Navigation_Diagram2.png)

## Classes and Templates Diagram
![](/images/classDiagram2.png)

## Team Members Participation

### Ignacio Carnal Bernal
- **Tasks:** 
- **5 most important commits**
  - [Commit 1]()
  - [Commit 2]()
  - [Commit 3]()
  - [Commit 4]()
  - [Commit 5]()
- **5 files with the most contributions**
  - [File 1]()
  - [File 2]()
  - [File 3]()
  - [File 4]()
  - [File 5]()

### Iker Morante Aceituno
- **Tasks:** 

- **5 most important commits**
  - [Commit 1]()
  - [Commit 2]()
  - [Commit 3]()
  - [Commit 4]()
  - [Commit 5]()
- **5 files with the most contributions**
  - [File 1]()
  - [File 2]()
  - [File 3]()
  - [File 4]()
  - [File 5]()


