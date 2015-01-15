## Working with Snapshots

The default mechanism for sbt to cache build products is with a local [ivy](http://ant.apache.org/ivy/) cache, e.g. 
`~/.ivy2/cache/`.

Ivy does not understand or handle snapshot builds well, so one must manually remove a locally published/cached 
library when switching between using a released version and a snapshot version.  

To use hyperscala from a snapshot one should do the following.

0. Locally clone [powerscala](https://github.com/darkfrog26/power), then from the clone, `sbt publishLocal`.

0. Locally clone [outrnet](https://github.com/darkfrog26/outrnet), then from the clone, `sbt publishLocal`.

0. Locally clone [hyperscala](https://github.com/darkfrog26/hyperscala), then from the clone, `sbt publishLocal`.

You may wish to build local versions of the docs while you are in these. The hyperscala project contains many 
sub projects so to build a unified set of local docs you should `sbt unidoc`.  

If you later need to work with a non-snapshot, or different snapshot release of a given library, you will need to 
remove the library from the local ivy cache, so the correct version is cached.  

