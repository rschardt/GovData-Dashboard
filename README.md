# GovData-Dashboard

This repository is related to this Coding Challenge: https://github.com/digitalservicebund/backend-challenge

![alt text GovData-Dashboard.png](https://github.com/rschardt/GovData-Dashboard/blob/main/GovData-Dashboard.png)

## Run
Not using nix?

Install clojure for running the source, the REPL and the unit tests.

The app also has a docker image: ghcr.io/rschardt/govdata-dashboard:latest

### run from source
```
nix develop -c clj -M -m main.core
```

## Run REPL

### start REPL
```
nix develop -c clj
```

### run main.core inside REPL
```
(require '[main.core])
(main.core/-main)
```

### reload namespace
```
(require '[main.core] :reload-all)
```

## Tests

### run Unit Tests
```
nix develop -c clj -M -m test.main.core
```

## Build

### build binary
```
nix build
# run built binary
./result/bin/GovData-Dashboard
```

## Docker

### build docker image
```
nix build .\#docker-image
```

### load docker image from local build
```
docker image load -i result
```

### run docker image from local build
```
docker run ghcr.io/rschardt/govdata-dashboard:latest
```

### run docker image from ghrc.io
```
docker pull ghcr.io/rschardt/govdata-dashboard:latest
docker run ghcr.io/rschardt/govdata-dashboard:latest
```

## Regenerate Clojure dependencies for nix
If deps.edn has changed run the following command
and commit the resulting changes in deps-lock.json.
```
nix run github:jlesquembre/clj-nix#deps-lock
```

## External Libraries/Technologies
- For JSON-Parsing: https://github.com/dakrone/cheshire
- Simple http-server: https://github.com/http-kit/http-kit
- Hiccup for generating HTML: https://github.com/weavejester/hiccup
- A nix flake as devShell, run, test, build, packaging and deployment tool
- Nix support for clojure, clj-nix: https://github.com/jlesquembre/clj-nix
