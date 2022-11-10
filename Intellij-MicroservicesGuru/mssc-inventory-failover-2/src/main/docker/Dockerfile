###MultiStage Docker build

#java runtime env, we dont need the full blown jdk to run the project, we need a smaller runtime
FROM openjdk:11-jre-slim as builder
#working directory
WORKDIR application
#thise varaibles are defined in parent BOM (refer above images)
ADD maven/${project.build.finalName}.jar ${project.build.finalName}.jar
#using layer tools within our jar, we are going to extract out the layers
RUN java -Djarmode=layertools -jar ${project.build.finalName}.jar extract

##docker image build starts from here
FROM openjdk:11-jre-slim
LABEL PROJECT_NAME=${project.artifactId} \
      PROJECT=${project.id}

#working directroy of our application
EXPOSE 8083

WORKDIR application
##copy from builder(COPY --from=builder)  (builder from line 4),
#it has all the dependencies
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
#it has all the applications classes
COPY --from=builder application/application/ ./
#java=run java, '-Djava.security.egd=file:/dev/./urandom'=in some OS, if we dont do it this way will cause application to hang at times
#'org.springframework.boot.loader.JarLauncher'=lauch our application
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]