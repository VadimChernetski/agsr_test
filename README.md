# agsr_test

# create custom docker image for monitor service with predefine schema (provided command should be executed from root directory of this project)

sudo docker build -f Dockerfile_sensor_db -t agsr-postgres-sensor:latest .

# start db in container for monitor sensor service
sudo docker run \
-e POSTGRES_USER=agsr \
-e POSTGRES_PASSWORD=12345678 \
-e POSTGRES_DB=agsr \
-p 5432:5432 \
-d \
agsr-postgres-sensor:latest

# swagger monitor-service
http://localhost:8080/swagger-ui/index.html

# default users
nickname: viewer
pass: Qwerty123!
role: VIEWER

nickname: administrator
pass: Qwerty123!
role: ADMINISTRATOR

# create custom docker image for statistics service with predefine schema (provided command should be executed from root directory of this project)

sudo docker build -f Dockerfile_statistics_db -t agsr-postgres-sensor:latest .

# start db in container for statistics service
sudo docker run \
-e POSTGRES_USER=agsr \
-e POSTGRES_PASSWORD=12345678 \
-e POSTGRES_DB=agsr \
-p 5433:5432 \
-d \
agsr-postgres-sensor:latest

# swagger statistics-service
http://localhost:8081/swagger-ui/index.html