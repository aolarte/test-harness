import sys
x=sys.argv[1]
y=len(x)
print x
for i in range(0,y):
 for j in range(i,y):
  print x[i:j+1].rjust(j+1)