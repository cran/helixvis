# remove unnecessary files and compile the correct ones
# so that only necessary java binaries (*.class) are present
rm Sequence.jar
rm *.class
javac *.java

# include necessary binaries
# need to have helixvis-java in same directory as helixvis
jar cfe Sequence.jar Sequence *.class

# clean up after jar is created
rm *.class
