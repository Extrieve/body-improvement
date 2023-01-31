# Transactions to startup the H2 database for testing this is temporary
import requests
import json

with open('MOCK_DATA.json') as f:
    data = json.load(f)

url = 'http://localhost:8080/user/save'

for item in data:
    r = requests.post(url, json=item)
    if r.status_code != 200:
        print(r.status_code)
        print(r.text)

print('Done')