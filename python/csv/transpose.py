import csv, sys

source_fn="input.csv"
target_fn="output.csv"

rows = list(csv.reader(open(source_fn, "rb")))
with open(target_fn, "wb") as f:
    writer = csv.writer(f)
    for col in xrange(0, len(rows[0])):
        writer.writerow([row[col] for row in rows])

