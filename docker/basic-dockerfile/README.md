### Build docker image
```shell
docker build -t test-docker .
```

### Run docker container
```shell
docker run -d -p 8000:8000 test-docker
```

### Test
Open http://localhost:8000