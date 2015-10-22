# IVI YouTube Client

- [Overview] (#Overview)
- [Installation] (#Installation)

## <a name='Overview'>Overview<a/>

The IVI YouTube Client can automatically check your YouTube account subscriptions list and notify you when new videos are available.

## <a name='Installation'>Installation <a/>

This guide will show you how to build this project using Windows command line.

To successfully build the project you need the following tools:

 - JDK 8
 - Maven
 - Git

First clone the repository:

    git clone https://github.com/vladtkv/ivi.git

Note: Please don’t use “Download ZIP” button, because you will get repository without Git-Tags, which are required to successfully build the project.

You will get “ivi” folder. After that change directory to “ivi”:

    cd ivi

This folder contains main project “ivi” and Maven plugin “ivi-maven-plugin”, required to build the project. Install this plugin in Maven local repository. Type this:

    cd ivi-maven-plugin
    mvn install

After that you are ready to build main IVI project. Change directory to the main project “ivi”:

    cd ..\ivi

Last step is the project compilation:

    mvn compile

If you want to get the project in jar-file, please use this command:

    mvn package

That it. You have successfully built the project. Thank you for trying our IVI YouTube Client.
