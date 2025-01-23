# mongodb 
### UserProfileDB
```bash 
docker run --name mongodb \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=admin \
-e MONGO_INITDB_ROOT_PASSWORD=root\
-d mongodb/mongodb-community-server:latest
```

db.createUser({user: "irem", pwd: "root", roles: ["readWrite", "dbAdmin"]})


# Postgresql
### AuthDB
```bash 
docker run -d --name postgresql -e POSTGRES_PASSWORD=root -d postgres
```


# Redis
```bash
docker run -d --name microservice-redis -p 6379:6379 -d redis 
```

```bash
docker run --name redis-gui -d -p 8001:8001 redislabs/redisinsight:1.14.0
```

# Image creation with Dockerfile 
directory = configserver
```bash
docker build --platform linux/amd64 -t iremdagdeviren1/configserver:v01 . 
docker build --platform linux/amd64 -t iremdagdeviren1/authmicroservice:v01 . 
docker build --platform linux/amd64 -t iremdagdeviren1/usermicroservice:v01 . 

```