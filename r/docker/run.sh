#!/bin/bash

echo Connectiong to $andresolarte-tr2:us-central1:my-database-instance

/usr/local/cloud-sql-proxy $DB_INSTANCE &

Rscript test.R

