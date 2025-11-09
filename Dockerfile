# Use official Tomcat image
FROM tomcat:9.0

# Copy your web files into Tomcat webapps
COPY ./web /usr/local/tomcat/webapps/ROOT/
COPY ./src /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Expose port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]

