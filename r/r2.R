#Matrix
#Solve linear equations
coeffMatrix <-matrix(c(1,-1,8,-8,1,1,4,4,1,-1,2,-2,1,1,1,1), nrow =4)
constMatrix <-c(-3, -1, -13, -21)
solve(coeffMatrix, constMatrix)


colors <-c("Blue", "Red", "Red", "Blue", "Green", "Blue", "Red")
colorsFactor = factor(colors)
print(colorsFactor)
table(colorsFactor) #Gives the count for each factor
colorCodeFactor <-factor(colorsFactor, labels=c("B", "G", "R")) # Create a new factor fromm the old one

#IQR & std deviation
contender1 <- c(8.4,8.6,8.8,9,9,9.2,9.7,10.1,10.4,10.3,10.5,10.6,11.0,11.1,11.4,11.7,11.9,12.3,12.8,13,13,14.2,14.4,14.6)
contender2 <- c(9.8,9.8,9.9,10.1,10.1,10.2,10.2,10.3,10.3,10.7,10.8,10.8,11,11.1,11.2,11.2,11.3,11.6,11.7,11.7,11.8,11.8,11.9,11.9)
summary(contender1)
summary(contender2)
IQR(contender1)
IQR(contender2)
combined <- cbind(contender1,contender2) #Combine into an array. One column per dataset
View(combined) #Display in a nice view
boxplot(combined)
sd(contender1)
sd(contender2)