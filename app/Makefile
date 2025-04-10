.PHONY: build

n-dist:
	./build/install/app/bin/app

run:
	make run-dist

run-help:
	./build/install/app/bin/app -h

checkstyle:
	./gradlew checkstyleMain checkstyleTest

run-compare-easy:
	./build/install/app/bin/app ~/projects/java-project-71/app/src/test/resources/file1.json ~/projects/java-project-71/app/src/test/resources/file2.json

run-compare-yml:
	./build/install/app/bin/app ~/projects/java-project-71/app/src/test/resources/file1.yml ~/projects/java-project-71/app/src/test/resources/file2.yml

run-compare:
	./build/install/app/bin/app ~/projects/java-project-71/app/src/test/resources/file1_hard.json ~/projects/java-project-71/app/src/test/resources/file2_hard.json

run-compare-hard-plain:
	./build/install/app/bin/app -f plain ~/projects/java-project-71/app/src/test/resources/file1_hard.json ~/projects/java-project-71/app/src/test/resources/file2_hard.json

run-compare-hard-json:
	./build/install/app/bin/app -f json ~/projects/java-project-71/app/src/test/resources/file1_hard.json ~/projects/java-project-71/app/src/test/resources/file2_hard.json

build: test checkstyle
	./gradlew clean
	./gradlew installDist

test: # Run tests
	./gradlew test

lint: # Check code style
	./gradlew checkstyleMain checkstyleTest

test-report: # Check code coverage
	./gradlew jacocoTestReport