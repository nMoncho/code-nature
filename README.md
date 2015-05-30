# code-nature

Quil sketches designed to implement the examples or exercises from the book [The Nature of Code](http://natureofcode.com/)
My original idea was to learn clojure from a "real world" project after trying and failing a couple of times.

As a step forward from the original scope, I wish to also implement this with clojurescript and host it in my webpage (PENDING).

## Usage

Currently I'm using emacs with cider, it awesome, great clojure support as well as paredit.
Tried using LightTable but didn't stick, but you're more welcomed to use it if you like it more (not so many strange hot keys).

LightTable - open `core.clj` and press `Ctrl+Shift+Enter` to evaluate the file.

Emacs - run cider, open `core.clj` and press `C-c C-k` to evaluate the file.

REPL - run `(require 'code-nature.core)`.

While nothing prevents you from running the project anyway you like with the methods defined above, since the project will define a lot of sketches I wish that you could do something like this:

`lein run sketch-namespace sketch-name`. (PENDING)

So you can run different sketches with the same command.

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
