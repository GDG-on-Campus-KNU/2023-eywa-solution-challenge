import json
import csv
from connection import curs, conn

def delete_table():
  # my sql connection
  sqls = ["SET FOREIGN_KEY_CHECKS = 0;", "DELETE FROM `Animal`;", "DELETE FROM `Dictionary`;", "DELETE FROM `Plant`;", "SET FOREIGN_KEY_CHECKS = 1;"]
  for sql in sqls:
    curs.execute(sql)
  conn.commit()