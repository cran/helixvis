# required for using rJava package
.onLoad <- function(libname, pkgname) {
  rJava::.jpackage(pkgname, lib.loc = libname)

  # DEBUG STUFF BELOW HERE
  # print("Loaded jar.")
  # obj <- .jnew("Sequence", "")
  # print("Successfully initialized class.")
}

# used primarily for welcome message
.onAttach <- function(libname, pkgname) {
  packageStartupMessage("Welcome to the helixvis package.")
}
