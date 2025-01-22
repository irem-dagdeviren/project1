#mongodb 
##UserProfileDB
```bash 
docker run --name mongodb \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=admin \
-e MONGO_INITDB_ROOT_PASSWORD=root\
-d mongodb/mongodb-community-server:latest
```

db.createUser({user: "irem", pwd: "root", roles: ["readWrite", "dbAdmin"]})


#Postgresql
##AuthDB
```bash 
docker run -d --name postgresql -e POSTGRES_PASSWORD=root -d postgres
```
