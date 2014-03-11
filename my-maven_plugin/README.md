$ mvn archetype:generate \
   -DgroupId=sample.plugin \
   -DartifactId=hello-maven-plugin \
   -DarchetypeGroupId=org.apache.maven.archetypes \
   -DarchetypeArtifactId=maven-archetype-plugin

1. how to verify hello-maven-plugin?
$ mvn sample.plugin:hello-maven-plugin:1.0-SNAPSHOT:touch
