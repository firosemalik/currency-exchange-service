docker build -t currency-exchange-service:v1 .

docker run -d -p 8000:8000 currency-exchange-service:v1

docker run -p 9411:9411 openzipkin/zipkin:2.23