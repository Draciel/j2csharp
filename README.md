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

6. Handy shortcuts
         
         Ctrl + Shift + G -> compile grammar
