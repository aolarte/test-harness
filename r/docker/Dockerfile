FROM r-base
RUN apt-get update && apt-get -y install libpq-dev && install.r DBI && install.r RPostgres 
COPY cloud-sql-proxy /usr/local/

COPY test.R /usr/local/src
COPY run.sh /usr/local/src
WORKDIR /usr/local/src
RUN chmod a+x /usr/local/src/run.sh && chmod a+x /usr/local/cloud-sql-proxy
CMD ["/usr/local/src/run.sh"]