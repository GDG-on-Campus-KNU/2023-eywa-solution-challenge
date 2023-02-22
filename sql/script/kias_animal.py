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

  ecological_key = ['habitat', 'food_chain', 'lifespan', 'etc']
  introduction_key = ['origin', 'period', 'purpose']
  effect_key = ['ecosystem', 'entity']
  regulate_key = ['past', 'reason', 'method']
  designation_key = ['domestic', 'overseas', 'organization']

  for idx, row in enumerate(reader):
    _id, korean_name, english_name, kind = row
    page = wiki_wiki.page(english_name)

    if kind != 'plant' and page.exists():
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
      image = driver.find_element(By.XPATH, r'//dt[@class="taC"]/img').get_attribute('src')
      entity['image'] = image
      
      # 형태 특성
      shape = driver.find_element(By.XPATH, r'//dl[@class="listinfo"][1]/dd[1]').text
      entity['shape'] = translator.translate(shape, dest="en").text
      
      # 생태 특성
      entity['ecological'] = dict()
      for i, key in zip(range(1, 6), ecological_key):
        if i != 2:
          ecological = driver.find_element(By.XPATH, r'//dl[@class="listinfo"][2]/dd[' + str(i) + ']').text
          entity['ecological'][key] = translator.translate(ecological, dest="en").text
      
      # 도입 특성
      entity['introduction'] = dict()
      for i, key in zip(range(1, 4), introduction_key):
        introduction = driver.find_element(By.XPATH, r'//dl[@class="listinfo"][3]/dd[' + str(i) + ']').text
        entity['introduction'][key] = translator.translate(introduction, dest="en").text

      # 분포 특성
      distribution = driver.find_element(By.XPATH, r'//dl[@class="listinfo"][4]/dd[1]').text
      entity['distribution'] = translator.translate(distribution, dest="en").text

      # 영향
      entity['effect'] = dict()
      if idx == 6:
        for i, key in zip(range(1, 3), effect_key):
          effect =  driver.find_element(By.XPATH, r'//*[@id="subcontents"]/div[5]/div/i/i/i/dl[1]/dd[' + str(i) + ']').text
          entity['effect'][key] = translator.translate(effect, dest="en").text
      else:
        for i, key in zip(range(1, 3), effect_key): 
          effect = driver.find_element(By.XPATH, r'//dl[@class="listinfo"][6]/dd[' + str(i) + ']').text
          entity['effect'][key] = translator.translate(effect, dest="en").text

      # 조절/퇴치
      entity['regulate'] = dict()
      if idx == 6:
        for i, key in zip(range(1, 4), regulate_key):
          regulate = driver.find_element(By.XPATH, r'//*[@id="subcontents"]/div[5]/div/i/i/i/dl[2]/dd[' + str(i) + ']').text
          entity['regulate'][key] = translator.translate(regulate, dest="en").text
      else:
        for i, key in zip(range(1, 4), regulate_key):
          regulate = driver.find_element(By.XPATH, r'//dl[@class="listinfo"][7]/dd[' + str(i) + ']').text
          entity['regulate'][key] = translator.translate(regulate, dest="en").text

      # 국내외 침입외래종 지정현황
      entity['designation'] = dict()
      if idx == 6:
        for i, key in zip(range(1, 4), designation_key):
          designation = driver.find_element(By.XPATH, r'//*[@id="subcontents"]/div[5]/div/i/i/i/dl[3]/dd[' + str(i) + ']').text
          entity['designation'][key] = translator.translate(designation, dest="en").text
      else:
        for i, key in zip(range(1, 4), designation_key):
          designation = driver.find_element(By.XPATH, r'//dl[@class="listinfo"][8]/dd[' + str(i) + ']').text
          entity['designation'][key] = translator.translate(designation, dest="en").text

      with open(f'data/{kind}/{english_name}.json', 'w', encoding='utf-8') as make_file:
        json.dump(entity, make_file, indent="\t")
      
      print(f"{idx} {english_name} done")
    else:
      if kind != 'plant':
        print(f"{english_name} failed")
        failed.append(english_name)
  print(failed)