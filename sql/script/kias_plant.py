import wikipediaapi
import csv
import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from googletrans import Translator

translator = Translator()
driver = webdriver.Chrome('/Users/sinchangyu/chromedriver/chromedriver')
driver.implicitly_wait(3)

with open('csv/ecosystem_disruptor.csv', newline='') as csvfile:
  reader = csv.reader(csvfile, delimiter=',', quotechar='"')
  wiki_wiki = wikipediaapi.Wikipedia('en')
  failed = []

  shape_key = ['size', 'stem', 'leaf', 'description', 'fruit', 'color']
  ecological_key = ['growth_period', 'bloom_period']
  habitat_key = ['domestic', 'oversea']
  introduction_key = ['origin', 'period', 'purpose']

  for idx, row in enumerate(reader):
    _id, korean_name, english_name, kind = row
    page = wiki_wiki.page(english_name)
    if kind == 'plant' and page.exists():
      entity = dict()
      entity['id'] = _id
      entity['korean_name'] = korean_name
      entity['english_name'] = english_name
      entity['summary'] = page.summary.replace("\n", " ")
      entity['kind'] = kind
      
      driver.get('https://kias.nie.re.kr/home/for/for02001l.do?searchClsGbn=eco')
      driver.find_element(By.NAME, 'searchKeyword').send_keys(korean_name)
      driver.execute_script('goSearch('')')
      driver.find_element(By.XPATH, r'//div[@class="thumwrap mar_t20"]/ul/li/ol/li[2]/dl/dt/a').click()
      
      # 이미지 경로
      entity['image'] = driver.find_element(By.XPATH, r'//dt[@class="taC"]/img').get_attribute('src')
      
      # 형태 특성
      entity['shape'] = dict()
      entity['shape']['flower'] = dict()
      for i, key in zip(range(1, 10), shape_key):
        if key == 'description' or key == 'color':
          entity['shape']['flower'][key] = translator.translate(driver.find_element(By.XPATH, r'//dl[@class="listinfo"][1]/dd[' + str(i) + ']').text, dest="en").text
        else:
          entity['shape'][key] = translator.translate(driver.find_element(By.XPATH, r'//dl[@class="listinfo"][1]/dd[' + str(i) + ']').text, dest="en").text
      
      # 생태 특성
      entity['ecological'] = dict()
      entity['ecological']['habitat'] = dict()
      for i, key in zip(range(1, 3), ecological_key):
        entity['ecological'][key] = translator.translate(driver.find_element(By.XPATH, r'//dl[@class="listinfo"][2]/dd[' + str(i) + ']').text, dest="en").text
      for i, key in zip(range(1, 3), habitat_key):
        entity['ecological']['habitat'][key] = translator.translate(driver.find_element(By.XPATH, r'//table[@class="bbs_list mar_t10"]/tbody/tr[2]/td[' + str(i) + ']').text, dest="en").text
      
      # 도입 특성
      entity['introduction'] = dict()
      for i, key in zip(range(1, 3), introduction_key):
        entity['introduction'][key] = translator.translate(driver.find_element(By.XPATH, r'//table[@class="bbs_list mar_t20"]/tbody[2]/tr/td[' + str(i) + ']').text, dest="en").text

      with open(f'data/{kind}/{english_name}.json', 'w', encoding='utf-8') as make_file:
        json.dump(entity, make_file, indent="\t")

      print(f"{idx} {english_name} done")
    else:
      if kind == 'plant':
        print(f"{english_name} failed")
        failed.append(english_name)
  print(failed)