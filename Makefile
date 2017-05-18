all:
	if [ ! -d './bin' ]; then mkdir bin; fi
	javac -d './bin' -cp './lib/json.jar' `find './src' -name *.java`
run:
	java -cp './bin:./lib/json.jar' XFacteur.XFacteur

