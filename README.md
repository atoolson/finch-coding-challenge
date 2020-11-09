# Finch Backend Coding Challenge

by Andrew Toolson

## Overview

This application is built in Java using the Spring Boot framework. The Controllers discover all
manufacturer-specific data resolvers and route web requests to the appropriate one based on whether
that resolver says it can support the request.

## Running

Run `./gradlew bootRun` to start the project. The application binds to port 8080 and 
[http://localhost:8080/api](http://localhost:8080/api) is a good place to start poking around.

## Tracing

Tracing is implemented with the [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth)
which ties all logs from a specific request together, hands request and span IDs on to other web services
through HTTP headers, and integrates with other tracing frameworks like [Zipkin](https://zipkin.io/).

## Testing

The project is tested through two types of unit tests:
1. Class level unit tests
1. HTTP-based unit tests

The class level unit tests exercise the business logic of specific classes, wile the HTTP endpoint unit
tests exercise the HTTP features of the application. If I had more time to devote to this challenge I
would have added integration tests that exercise the "GM" API as well.

## Retry Logic

Since external services are not bulletproof this project uses robust retry logic when there are failures.
The bulk of this logic resides in RestClient.java. All operational errors (network unreachable, connection 
reset, etc) are retried 5 times (including the initial try) with 3 seconds between tries. 429 errors also
follow this logic.

I'd build upon this by having more granular control over retries. The 5 retries/3 second back-off defaults
are pretty sane, but there may be some manufacturers with flakier APIs that warrent more retries, or longer
back-off periods.

## API Definition

The structure of the data returned is not coupled with GM's API and is meant to look more like the ideal data
structure developers would want from such an API. I don't have tons of experience in car details so I may be 
off on how many types of drivetrains there are, I feel like the format would fit GM's API equally well as Fords
or Teslas.

## Easy of Adoption

I included OpenAPI (Swagger) in this project to make it very easy for other developers to adopt. You can see the
UI at http://localhost:8080/api. Another improvement I would make is to include [Postman](https://www.postman.com/)
examples so developers can quickly get started with our API. I'd also add some web-friendly documentation along the
lines of [readthedocs.org](https://readthedocs.org/) or [mkdocs.org](https://www.mkdocs.org/), and perhaps some
examples using `curl`.

### Examples

Here are a few examples to get started:
```bash
curl 'http://localhost:8080/api/v1/vehicle-info/1234'

curl 'http://localhost:8080/api/v1/security-status/1235'

curl 'http://localhost:8080/api/v1/fuel-battery-status/1234'

curl -X POST "http://localhost:8080/api/v1/start-stop-vehicle" -H "Content-Type: application/json" -d '{"vehicleId":"1234","command":"START"}'
```

## Other Improvements

In a real-world setting I'd include other RPC mechanisms, like GraphQL or gRPC. Also, security is something I
didn't bother with as a homegrown solution requires huge amounts of testing, and implementing an off-the-shelf
solution doesn't demonstrate much experience.