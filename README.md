Project for course "Języki formalne i kompilatory"

Configuration without Gradle:

1. Download latest ANTLR version from (in this quickstart guide I will use 4.7.2)

        http://www.antlr.org/download/

2. Move ANTLR to /usr/local/lib 

        sudo cp antlr-4.7.2-complete.jar /usr/local/lib/

3. Export classpath

        export CLASSPATH = ".:/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH"

4. Add some handy aliases:

        alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH" org.antlr.v4.Tool'

        alias grun='java org.antlr.v4.gui.TestRig'

5. Install ANTLR4 plugin for Intellij

6. Once you install all necessary things, right click on grammar file, choose **configure ANTLR** option and clean output directory if necessary

7. Open grammar file and generate lexer, parser and other things (Ctrl + Shift + G or right click on file and choose **Generate ANTLR Recognizer** 
         
8. Open File -> Project Structure and mark **gen** directory as source

9. Build project and run Main.main(args) 

10. Have fun!

Configuration with Gradle:

1. Build project from terminal via command:
    
        ./gradlew build

2. You can run project via command: 
        
        ./gradlew run --args='path'

Path can be directory or direct path to Java class

Translation output will be in dir `translated` 