lateness <-c(40, 30, 20, 45, 60, 120, 35, 40, 50, 55, 53, 22, 27, 48, 62, 33, 35, 40 ,45)
range(lateness)
bins <-seq(20,130,by=10)
intervals <-cut(lateness,bins,right=FALSE)
table(intervals)
#Manual histogram
plot(table(intervals), type ="h", main ="Rabbit Arrival Histogram", xlab ="Intervals", ylab ="Frequency" )
#One step histogram
hist(lateness,breaks =bins)

#Mean / Median Mode
mean(lateness)
median(lateness)
#Finding the mode (most common element)
sort(table(lateness),decreasing =TRUE)[1]

#Data Types
textVar = "Hello"
numVar = 1.6
class(textVar)
class(numVar)
is.double(numVar)
is.numeric(numVar)
is.integer(numVar)
intVar=as.integer(1.1 + 1.2)
is.integer(intVar)

boolVar = TRUE
class(boolVar)

charVar = "any string"
class(charVar)


dateVar = as.Date("2017-01-01 00:00")
class(dateVar)
timestampVar = as.POSIXct("2017-01-01 00:00")
class(timestampVar)


#Vector: 1 dimentional. Same type
#List: 1 dimentional. Any type (even a list)
#Array: n dimentional. Same type
#Matrix: 2 dimentional array. Same type
#Datafram: Data arranged in columns and rows

#Create a vector
vectorVar = c(40, 30, 20, 45)
mode(vectorVar) # Display the type of the vector
emptyVector = numeric(5) # Create an empty array, numeric, size 5
concatenatedVector = c(vectorVar, emptyVector) # c will concatenate 2 vectors into one
length(concatenatedVector)
vectorPlusOne = vectorVar + 1  # plus one applied to every element in array
print(vectorVar)
print(vectorPlusOne)
#Aggregate functions
sum(vectorVar) 
prod(vectorVar) #product
mean(vectorVar)

simpleSequence = 1:10
rep(simpleSequence, times =2, length.out =20, each =3) #repeat a sequence
seq(from = 5, to =10, by =0.5) #generate a sequence


stringSequence <-c("test", "long string", "A")
nchar(stringSequence)

#Using paste to concatenate 
simpleSequence <-1:4
stringSequence <-c("A", "B", "C", "D", "E", "F", "G", "H")
paste(stringSequence, simpleSequence, sep="")


#IMPORTANT!!! :INDEXING IN R STARTS FROM 1

#Named Vector
namedVector = 1:5
namedVector
names(namedVector) <- c("A","B","C","D","E") #How does the <- operator work here ???
namedVector

