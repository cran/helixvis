# Why should I use helixvis?

helixvis has the following advantages over most web servers that draw helical wheels
and/or wenxiang diagrams:

* **helixvis handles both types of diagrams.** Most web servers draw either helical
wheels or wenxiang diagrams; having a single tool that can draw both saves time.

* **helixvis handles multiple sequences.** Most web servers take a single input sequence,
and require the user to download each sequence's corresponding visualization individually.
The ability to generate a visualization for an entire database of sequences with a single
line of code makes helixvis a useful tool.

* **helixvis is installed locally.** An internet connection is not required to use
helixvis.

* **helixvis is open-source.** With an active, public GitHub repository
(<https://github.com/rrrlw/helixvis>), the code for helixvis is available to all users.
Contributions, issue reports, and bug fixes are encouraged!

# How do I use helixvis?

The helixvis package has two primary workhorse functions:

* `draw_wheel`

* `draw_wenxiang`

# How do I get helixvis?

You can download helixvis by using the following R code:

```r
install.packages("devtools")
devtools::install_github("rrrlw/helixvis")
```

We hope that helixvis will soon be available on CRAN.

# Upcoming features

The following features are being planned for a minor release (or many minor releases):

* Customizable image sizes (instead of only the default 600x600 pixels)

* Customizable color choices for individual residues

* Faster runtime for `draw_*` functions

The following features are being planned for a major release:

* **Helical nets.** Although an older form of visualization, helical nets still have
utility. A `draw_net` function is the goal.

# Contact

Please report any issues at <https://github.com/rrrlw/helixvis/issues>. Find more contact
information in the DESCRIPTION file at the top level of the helixvis GitHub repository at
<https://github.com/rrrlw/helixvis>.