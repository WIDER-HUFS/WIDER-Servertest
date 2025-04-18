FROM mysql:8.0

# Set environment variables (optional for build-time clarity; actual values should be passed at runtime via docker-compose or CLI)
ENV MYSQL_DATABASE=widerdb \
    MYSQL_USER=hoo

# Expose MySQL port
EXPOSE 3306

# (Optional) Copy initialization scripts
COPY ./init.sql /docker-entrypoint-initdb.d/