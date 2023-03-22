import json
import requests
import os

for kind in os.listdir("../data"):
  for dictionary in os.listdir("../data/" + kind):
    with open("../data/" + kind + "/" + dictionary, "r") as f:
      data = json.load(f)
      # res = requests.post("https://eywa-server-cc6ds2skiq-du.a.run.app/dictionary", json=data)
      res = requests.post("http://localhost:8080/dictionary", json=data)
      if res.status_code == 200:
        print("Success: " + dictionary)
      else:
        print("Error: " + dictionary)
        break