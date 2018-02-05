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
  
  # check Java at runtime (in accordance with "Writing R extensions")
  rJava::.jinit()
  jv <- rJava::.jcall("java/lang/System", "S", "getProperty",
               "java.runtime.version")
  if (substr(jv, 1L, 1L) == "1") {
    jvn <- as.numeric(paste0(strsplit(jv, "[.]")[[1L]][1:2], collapse = "."))
    if (jvn < 1.8) stop("Java 8 is needed for this package but not available.")
  }
  
  packageStartupMessage("Welcome to the helixvis package.")
}
