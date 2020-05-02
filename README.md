# Sample IBM MQ environment

Quickly setup a test MQ docker container and test with sample producer and consumer application

## Build the MQ docker container

Build a custom docker container from the official IBM MQ container image using the instructions in Dockerfile

```bash

docker build --pull --rm -f "Dockerfile" -t mq:1.0 "."

```

Run the custom built docker container in interactive mode

```bash

docker run -d -p 1414:1414/tcp -p 9157:9157/tcp mq:1.0

```

## Producer mode

Run the sample app in producer mode to put message on the queue

```bash

java -jar .\mq-samples.jar -l false

```

## Consumer mode

Run the sample app in consumer mode to get messages from the queue

```bash

java -jar .\mq-samples.jar -l true

```

## Useful arguments

Run the sample app with --help parameter to show various parameter options

```bash

java -jar .\mq-samples.jar -help

```

One useful argument is the "-n {numMessages}" where numMessage is the number to messages to get or put to the queue. By default this value is 1.
