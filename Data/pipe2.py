import pandas as pd

train = pd.read_csv('pipe1_output.csv')

train.dropna(subset=['historic'], how='any', inplace=True)

train.dropna(subset=['name'], how='any', inplace=True)

condition = ~train['name'].str.match(r'^[\d\s.-]+$', na=False)

filtered_df = train[condition]
dropped = filtered_df.drop(columns=['artwork'])
dropped.to_csv('final_data.csv', index=False)
