import csv

# Initialize an empty list to store the data
data = []

# Read data from the input.txt file
with open('input.txt', 'r', encoding='utf-8') as input_file:
    lines = input_file.readlines()
    header = lines[0].strip().split('\t')
    for line in lines[1:]:
        values = line.strip().split('\t')
        data.append(dict(zip(header, values)))

# CSV file name
csv_file = 'output.csv'

# Open the CSV file for writing
with open(csv_file, 'w', newline='', encoding='utf-8') as f:
    writer = csv.DictWriter(f, fieldnames=header)

    # Write the header row
    writer.writeheader()

    # Write the data rows
    writer.writerows(data)

print(f'Data has been written to {csv_file}')
