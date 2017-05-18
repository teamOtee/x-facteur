all:
	javac -d './bin' -cp '.:./json.jar' `find src -name *.java`
run:
	java -cp './bin:./json.jar' XFacteur.XFacteur

