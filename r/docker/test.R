

db = "test"   # Specify the name of your Database
host_db = "localhost"  
db_port = "5432"                # Specify your port number. e.g. 98939
db_user = "test"         # Specify your username. e.g. "admin"
db_password <- Sys.getenv("DB_PASSWORD")


library(RPostgres)
library(DBI)

tryCatch({
    print("Getting Driver...")

    print("Connecting to Database...")
    con <- dbConnect(RPostgres::Postgres(), dbname = db, host=host_db, port=db_port, user=db_user, password=db_password)  
    print("Database Connected!")
    print("Listing tables")
    print(dbListTables(con))
    print("Listed tables")

 
    },
    error=function(cond) {
        print(cond)
            print("Unable to connect to Database.")
    })