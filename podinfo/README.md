参考https://github.com/stefanprodan/podinfo

Web API:

* GET / prints runtime information
* GET /version prints podinfo version and git commit hash
* GET /metrics return HTTP requests duration and Go runtime metrics
* GET /healthz used by Kubernetes liveness probe
* GET /readyz used by Kubernetes readiness probe
* POST /readyz/enable signals the Kubernetes LB that this instance is ready to receive traffic
* POST /readyz/disable signals the Kubernetes LB to stop sending requests to this instance
* GET /status/{code} returns the status code
* GET /panic crashes the process with exit code 255
* POST /echo forwards the call to the backend service and echos the posted content
* GET /env returns the environment variables as a JSON array
* GET /headers returns a JSON with the request HTTP headers
* GET /delay/{seconds} waits for the specified period
* POST /token issues a JWT token valid for one minute JWT=$(curl -sd 'anon' podinfo:9898/token | jq -r .token)
* GET /token/validate validates the JWT token curl -H "Authorization: Bearer $JWT" podinfo:9898/token/validate
* GET /configs returns a JSON with configmaps and/or secrets mounted in the config volume
* POST /store writes the posted content to disk at /data/hash and returns the SHA1 hash of the content
* GET /store/{hash} returns the content of the file /data/hash if exists
* GET /ws/echo echos content via websockets podcli ws ws://localhost:9898/ws/echo
* GET /chunked/{seconds} uses transfer-encoding type chunked to give a partial response and then waits for the specified period
* GET /swagger.json returns the API Swagger docs, used for Linkerd service profiling and Gloo routes discovery


gRPC API:
* /grpc.health.v1.Health/Check health checking