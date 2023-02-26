import pymysql

conn = pymysql.connect(
  host="localhost",
  port=3306,
  user="scv1702",
  password="1234",
  database="eywa",
  charset="utf8",
)
curs = conn.cursor()