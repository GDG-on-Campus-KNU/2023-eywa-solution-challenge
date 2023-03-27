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

level = {
  1: {
    # "level": "1",
    "min_exp": "0",
    "max_exp": "100"
  },
  2: {
    # "level": "2",
    "min_exp": "100",
    "max_exp": "300"
  },
  3: {
    # "level": "3",
    "min_exp": "300",
    "max_exp": "600"
  },
  4: {
    # "level": "4",
    "min_exp": "600",
    "max_exp": "1000"
  },
  5: {
    # "level": "5",
    "min_exp": "1000",
    "max_exp": "1500"
  },
  6: {
    # "level": "6",
    "min_exp": "1500",
    "max_exp": "2100"
  },
  7: {
    # "level": "7",
    "min_exp": "2100",
    "max_exp": "2800"
  },
  8: {
    # "level": "8",
    "min_exp": "2800",
    "max_exp": "3600"
  },
  9: {
    # "level": "9",
    "min_exp": "3600",
    "max_exp": "4500"
  },
  10: {
    # "level": "10",
    "min_exp": "4500",
    "max_exp": "5500"
  }
}


res = requests.post("http://localhost:8080/level", json=level)
if res.status_code == 200:
  print("Success: level")
else:
  print("Error: level")


