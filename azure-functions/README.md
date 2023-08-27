## Tutorial Source
https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/getting-started-with-spring-cloud-function-in-azure

# Run locally

## Build and run
```shell
cd ~/code-snippets/azure-functions
mvn clean package
mvn azure-functions:run
```

## Test
```shell
curl -X POST http://localhost:7071/api/hello -d "{\"name\":\"Azure\"}"
```

# Run on Azure

## Deploy
```shell
az login
mvn azure-functions:deploy
```

## Test
```shell
curl https://sample-function-666.azurewebsites.net/api/hello -d "{\"name\":\"Azure\"}"
```

## Clean up all resources
```shell
az group delete \
    --name sample-resource-group \
    --yes
```


# Useful info
In case of error "Failed to discover main class"
```text
with latest dependencies it works if specify

MAIN_CLASS in local.settings.json
MAIN_CLASS in src/main/azure/local.settings.json
start-class in pom.xml
```
https://github.com/Azure/azure-functions-java-worker/issues/338  
https://github.com/microsoft/azure-maven-plugins/issues/912