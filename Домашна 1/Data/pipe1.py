import csv

data = []

with open('input.txt', 'r', encoding='utf-8') as input_file:
    lines = input_file.readlines()
    header = lines[0].strip().split('\t')
    for line in lines[1:]:
        values = line.strip().split('\t')
        data.append(dict(zip(header, values)))

csv_file = 'pipe1_output.csv'

with open(csv_file, 'w', newline='', encoding='utf-8') as f:
    writer = csv.DictWriter(f, fieldnames=header)

    writer.writeheader()

    writer.writerows(data)

