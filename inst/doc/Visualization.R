## ----setup, echo = FALSE, results = "hide"-------------------------------
# given in "R Packages" by Wickham for displaying comments
knitr::opts_chunk$set(collapse = TRUE, comment = "#>")

## ----aa_table, echo = FALSE, fig.cap = "List of 22 proteinogenic amino acids supported for visualization by helixvis."----
aa_data <- data.frame(Name = c("Alanine",
                               "Arginine",
                               "Asparagine",
                               "Aspartic Acid",
                               "Cysteine",
                               "Glutamic Acid",
                               "Glutamine",
                               "Glycine",
                               "Histidine",
                               "Isoleucine",
                               "Leucine",
                               "Lysine",
                               "Methionine",
                               "Phenylalanine",
                               "Proline",
                               "Serine",
                               "Threonine",
                               "Tryptophan",
                               "Tyrosine",
                               "Valine",
                               "Selenocysteine",
                               "Pyrrolysine"),
                      Code = c("A",
                               "R",
                               "N",
                               "D",
                               "C",
                               "E",
                               "Q",
                               "G",
                               "H",
                               "I",
                               "L",
                               "K",
                               "M",
                               "F",
                               "P",
                               "S",
                               "T",
                               "W",
                               "Y",
                               "V",
                               "U",
                               "O"),
                      Type = c("Hydrophobic",
                               "Charged",
                               "Hydrophilic",
                               "Charged",
                               "Hydrophilic",
                               "Charged",
                               "Hydrophilic",
                               "Hydrophobic",
                               "Charged",
                               "Hydrophobic",
                               "Hydrophobic",
                               "Charged",
                               "Hydrophobic",
                               "Hydrophobic",
                               "Hydrophilic",
                               "Hydrophilic",
                               "Hydrophilic",
                               "Hydrophobic",
                               "Hydrophobic",
                               "Hydrophobic",
                               "Hydrophilic",
                               "Charged"))
knitr::kable(aa_data)

## ----wheel_code----------------------------------------------------------
helixvis::draw_wheel("Test Wheel", "AKDEWWREKLYIGYWREL")

## ----wheel_image, echo = FALSE, fig.cap = "Helical wheel for the sequence AKDEWWREKLYIGYWREL."----
knitr::include_graphics("Test Wheel.png")

## ----mult_wheel_code, eval = FALSE---------------------------------------
#  helixvis::draw_wheel(c("Test1", "Test2"),
#                       c("ABCDABCDABCDABCDAB",
#                         "DCBADCBADCBADCBADC"))

## ----wenxiang_code-------------------------------------------------------
helixvis::draw_wenxiang("Test Wenxiang", "AKDEWWREKLYIGYWREL")

## ----wenxiang_image, echo = FALSE, fig.cap = "Wenxiang diagram for the sequence AKDEWWREKLYIGYWREL."----
knitr::include_graphics("Test Wenxiang.png")

## ----mult_wenxiang_code, eval = FALSE------------------------------------
#  helixvis::draw_wenxiang(c("Test1", "Test2"),
#                          c("ABCDABCDABCDABCDAB",
#                            "DCBADCBADCBADCBADC"))

