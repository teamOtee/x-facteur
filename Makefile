all:
	javac -cp '.:json.jar' *.java

clean:
	rm *.class

run:
	java -cp '.:json.jar' XFacteur

