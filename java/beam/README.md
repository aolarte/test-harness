    mvn compile exec:java -Dexec.mainClass=com.andresolarte.beam.WordCount \
       -Dexec.args="--inputFile=pom.xml --output=counts" -Pdirect-runner
