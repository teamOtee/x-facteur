all:
	if [ ! -d './bin' ]; then mkdir bin; fi
	/usr/java/latest/bin/javac -d './bin' -cp './lib/json.jar' `find './src' -name *.java`
run: all
	/usr/java/latest/bin/java -cp './bin:./lib/json.jar' XFacteur.XFacteur

