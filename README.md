# iss-places-of-interest
Spring boot application for getting Places of Interest using International Space Station Current Location(ISS). 

### Pre-requisite
Make sure run.sh should be executable file.
 ```
chmod +x scripts/run.sh  
```

### To build and run the application using Dockerfile
> **Note:** Go to root directory. Make sure that your current directory is where your Dockerfile is present.

```shell
./scripts/run.sh <your-image-name>
```

### Run tests
```
mvn clean verify
```

### REST APIs

|HTTP Method | URL path | Description
|------------ | -------------| -------------|
GET|/iss_location|Get ISS location with latitude and longitude data.
GET|/iss_location/placesofinterest|Get places of interest base from current ISS location. It can also accepts params for latitude and longitude.

### Swagger documentation
- http://localhost:8080/swagger-ui/
