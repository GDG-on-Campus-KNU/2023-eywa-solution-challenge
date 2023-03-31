import json
import requests
import os
import sys

def insert_data(url):
  for kind in os.listdir("../data"):
    for dictionary in os.listdir("../data/" + kind):
      with open("../data/" + kind + "/" + dictionary, "r") as f:
        data = json.load(f)
        res = requests.post(f"{url}/dictionary", json=data)
        if res.status_code == 200:
          print("Success: " + dictionary)
        else:
          print("Error: " + dictionary)
          break
  print("Dictionary complete")
  with open("./level.json", "r") as f:
    level_list = json.load(f)
    for idx, level in enumerate(level_list):
      res = requests.post(f"{url}/level", json=level)
      if res.status_code == 200:
        print(f"Success: {idx+1} level")
      else:
        print(f"Error: {idx+1} level")
  print("Level complete")

if __name__ == '__main__':
    arguments = sys.argv
    if len(arguments) == 1:
        print("If you want to insert data at localhost, use -l option.")
        print("Or at deploy erver, use -d option.")
    elif arguments[1] == "-d":
      insert_data("https://eywa-server-cc6ds2skiq-du.a.run.app")
    elif arguments[1] == "-l":
      insert_data("http://localhost:8080")
    else :
      print("Unsupported Argument")