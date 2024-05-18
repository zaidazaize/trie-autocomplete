
.PHONY: compile package run

JAR_NAME=autocomplete-1.0-SNAPSHOT
ARGS=com.anurags.autocomplete.Main

compile:
	mvn compile
package:
	mvn package
run:
	java -cp target/$(JAR_NAME).jar $(ARGS)
