import schedule
import time
from kias_animal import crawl_animal
from kias_plant import crawl_plant
from sql_inserter import insert_data
from sql_deleter import delete_table

def crawl_and_insert():
  delete_table()
  time.sleep(1)
  crawl_animal()
  time.sleep(1)
  crawl_plant()
  time.sleep(1)
  insert_data()
  time.sleep(1)

schedule.every(2).weeks.do(crawl_and_insert)
    
while True:
    schedule.run_pending()
    time.sleep(1)