all:
	javac -d './bin' -cp '.:./json.jar' *.java
run:
	java -cp './bin:./json.jar' XFacteur.XFacteur

