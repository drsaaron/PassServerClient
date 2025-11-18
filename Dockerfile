FROM drsaaron/blazarjavabase:1.59

# add the source directory and mvn stuff
ADD ./pom.xml ./pom.xml
ADD ./src ./src
ADD ./mvnw ./mvnw
ADD ./.mvn ./.mvn

# build
RUN mvnw clean install

# add a shell script to run the java program
ADD ./runJava.sh ./runJava.sh

# run the script
CMD ./runJava.sh
