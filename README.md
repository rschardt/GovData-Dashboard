# GovData-Dashboard

This repository is related to this Coding Challenge: https://github.com/digitalservicebund/backend-challenge

# TODO Insert PNG of the Application Dashboard

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

### run unit tests
```
```

### run e2e tests
```
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
- A nix flake as devShell, run, test, build, packaging and deployment tool
- nix support for clojure, clj-nix: https://github.com/jlesquembre/clj-nix
