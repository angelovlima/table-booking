build:
	maven compile

unit-test:
	mvnw.cmd test

integration-test:
	mvnw.cmd test -P integration-test

system-test:
	mvnw.cmd test -P system-test

performance-test:
	mvnw.cmd test -P performance-test

docker-start-postgres:
	docker-compose up -d postgres

build-project:
	mvnw.cmd clean install

docker-start:
	docker-compose up --build