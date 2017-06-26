# File includes the functions to draw stuff (currently wheels and wenxiang)

# draw a helical wheel given the peptide sequence and name
# name = vector of names
# seq = vector of sequences
#' Create helical wheels to visualize alpha helical sequences.
#'
#' This function produces PNG files, one for each sequence in
#' \code{seq}, each of which contains a helical wheel visualizing
#' the sequence. Each sequence in the \code{seq} vector is of class
#' character, lists amino acid sequences from N-terminus to
#' C-terminus, and must contain a maximum of 18 characters. The
#' PNG files are stored in the current working directory (as
#' obtained by the \code{getwd()} command). The PNG files created are
#' all of the same size (600 x 600 pixels), with the residue in the upper
#' half of the wheel lying at the horizontal center representing the
#' amino acid at the N-terminus.
#'
#' @param name  character vector containing sequence names; sequence
#'   names can be empty, but \code{length(name)} must equal \code{length(seq)}.
#' @param seq   character vector containing residue sequences in order
#'   from N-terminus to C-terminus. Each sequence must contain at least
#'   two residues, and \code{length(name)} must equal \code{length(seq)}.
#' @examples
#' draw_wheel("TestWheel", "AREKHLVLLKREGDIWKL")
#' @export
draw_wheel <- function(name, seq) {
  # make sure lengths of names and sequences are equal
  if (length(name) != length(seq)) {
    stop("# of names must equal # of sequences.")
  }

  # make sure they're vectors of type character (name and seq)
  if (class(name) != "character" ||
      class(seq) != "character") {
    stop("Both variables must be of type character.")
  }

  # initialize Sequence object w/ emtpy string (not useful rn)
  obj <- rJava::.jnew("Sequence")

  # create all the wheel PNGs
  df <- data.frame(Name = name, Seq = seq)
  sapply(1:nrow(df),
         FUN = function(index) {
           rJava::.jcall(obj, "S", "drawStuff", as.character(df$Seq[index]),
                         as.character(df$Name[index]),
                         paste(getwd(), "/", df$Name[index], ".png", sep = ""),
                         "wheel")
         })
}

#' Create wenxiang diagrams to visualize alpha helical sequences.
#'
#' This function produces PNG files, one for each sequence in
#' \code{seq}, each of which contains a wenxiang diagram visualizing
#' the sequence. Each sequence in the \code{seq} vector is of class
#' character, lists amino acid sequences from N-terminus to
#' C-terminus, and must contain a maximum of 19 characters. The
#' PNG files are stored in the current working directory (as
#' obtained by the \code{getwd()} command). The PNG files created are
#' all of the same size (600 x 600 pixels), with the residue closest to
#' the center representing the amino acid at the N-terminus.
#'
#' @inheritParams draw_wheel
#' @examples
#' draw_wenxiang("TestWenxiang", "AREKHLVLLKREGDIWKL")
#' @export
draw_wenxiang <- function(name, seq) {
  # make sure lengths of names and sequences are equal
  if (length(name) != length(seq)) {
    stop("# of names must equal # of sequences.")
  }

  # make sure they're vectors of type character (name and seq)
  if (class(name) != "character" ||
      class(seq) != "character") {
    stop("Both variables must be of type character.")
  }

  # initialize Sequence object w/ emtpy string (not useful rn)
  obj <- rJava::.jnew("Sequence")

  # create all the wheel PNGs
  df <- data.frame(Name = name, Seq = seq)
  sapply(1:nrow(df),
         FUN = function(index) {
           rJava::.jcall(obj, "S", "drawStuff", as.character(df$Seq[index]),
                         as.character(df$Name[index]),
                         paste(getwd(), "/", df$Name[index], ".png", sep = ""),
                         "wenxiang")
         })
}
