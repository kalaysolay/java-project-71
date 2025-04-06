.PHONY: build

n-dist:
	./build/install/app/bin/app

run:
	make run-dist

run-help:
	./build/install/app/bin/app -h

checkstyle:
	./gradlew checkstyleMain checkstyleTest

run-compare:
	./build/install/app/bin/app ~/projects/java-project-71/app/src/test/resources/file1.json ~/projects/java-project-71/app/src/test/resources/file2.json

run-compare-yml:
	./build/install/app/bin/app ~/projects/java-project-71/app/src/test/resources/file1.yml ~/projects/java-project-71/app/src/test/resources/file2.yml

build: test checkstyle
	./gradlew clean
	./gradlew installDist

test: # Run tests
	./gradlew test

lint: # Check code style
	./gradlew checkstyleMain checkstyleTest

test-report: # Check code coverage
	./gradlew jacocoTestReport