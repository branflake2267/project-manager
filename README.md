##Project Manager
This is my experiment to speed up the creation of projects using Archetypes in Eclipse, IDEA and the terminal.

##Currently
I'm working on the desktop Archetype listing/edting for the Archetype directory. (02/28/2013)

##Source Highlights
* [Client - GWTP Desktop Archetype Directory](https://github.com/branflake2267/Project-Manager/tree/master/Directory/src/org/gonevertical/pm/directory/client) - GWTP application architecture used for the Desktop app.
* [Client - GWTP Desktop Rest Communication](https://github.com/branflake2267/Project-Manager/tree/master/Directory/src/org/gonevertical/pm/directory/client/rest) - Rest comm. & JSO
* [Server - GAE Rest Endpoints](https://github.com/branflake2267/Project-Manager/tree/master/Directory/src/org/gonevertical/pm/directory/server/rest) - Google App Engine endpoints used for rest communication.
* [Testing - GAE Rest Endpoints](https://github.com/branflake2267/Project-Manager/tree/master/DirectoryTesting/src/test/java/org/gonevertical/pm/directory/testing) - Using Rest Assured for testing GAE endpoints.

##Overall Goals
* Provide a directory of Archetypes.
* Provide an Eclipse plugin to create projects from the Archetype directory.
* Provide an IDEA plugin to create projects from Archetype directory.

##Archetype Directory Goals
* Filter by category, like GWT, GWTP, App Engine...
* Filter by tags, like the technology used to build archetype, JPA, SQL, MySQL, Tomcat…
* Provide users with choice.
* Provide the users favorites. 
* Provide tools/scripts to create Archetypes, build, test, deploy...

##Some Ideas for the Project
* Setup project bare bones in minutes. 
* Provide choice and allow others to contribute their archetype.
* Provide tutorial archetypes.
* IDE agnostic.

##Why
I was working on the GWTP eclipse plugin and didn't want to rewrite the static source writers and thought it would be faster to create the project from an Archetype. But I created a few to choose from and wanted the choice to choose the Archetype. So I figured it be faster to write my own plugin than wait for someone else to make something like this. In other words, why should spend time on redundant setups.
