# Use the official Tomcat image from Docker Hub
FROM tomcat:9.0

# Remove default ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy your project files to the Tomcat webapps directory
COPY ./ /usr/local/tomcat/webapps/ROOT/

# Expose default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
