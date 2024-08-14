{
  description = "A flake for GovData-Dashboard";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    clj-nix.url = "github:jlesquembre/clj-nix";
  };

  outputs = { self, nixpkgs, flake-utils, clj-nix }:

    flake-utils.lib.eachDefaultSystem (system:
    let
      pkgs = nixpkgs.legacyPackages.${system};
      name = "GovData-Dashboard";
      author = "rschardt";
    in
      {
        packages = rec {
          default = clj-nix.lib.mkCljApp {
            pkgs = pkgs;
            modules = [
              # Options: https://jlesquembre.github.io/clj-nix/options/
              {
                projectSrc = ./.;
                name = "${author}/${name}";
                main-ns = "main.core";
                #nativeImage.enable = true;
              }
            ];
          };

          docker-image = pkgs.dockerTools.buildLayeredImage {
            name = "ghcr.io/rschardt/govdata-dashboard";
            tag = "latest";
            config = {
              Cmd = "${default}/bin/${name}";
            };
          };
        };

        devShells.default = pkgs.mkShell {
          packages = with pkgs; [
            clojure
          ];
        };
      });
}
