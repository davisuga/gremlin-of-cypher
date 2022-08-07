# Gremlin to Cypher API

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## How to use it

Make a GET request to
http://localhost:3000/?mode=[cosmos/neptune/tinker]&cypher=[cypher query content]
