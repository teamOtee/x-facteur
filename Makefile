all:
	/usr/java/latest/bin/javac -cp '.:json.jar' *.java

clean:
	rm *.class

run:
	/usr/java/latest/bin/java -cp '.:json.jar' XFacteur

