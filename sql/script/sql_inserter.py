import json
import csv
import pymysql

# my sql connection
conn = pymysql.connect(
    host = "localhost",
    port=3306,
    user="scv1702",
    password="1234",
    database="eywa",
	  charset="utf8",
)
curs = conn.cursor()

# sql insert template
dict_template = "INSERT INTO Dictionary(korean_name, english_name, summary, kind, image) VALUES(%s, %s, %s, %s, %s)"
animal_template = "INSERT INTO Animal(shape, ecological_habitat, ecological_lifespan, ecological_etc, introduction_origin, introduction_period, introduction_purpose, distribution, effect_ecosystem, effect_entity, regulate_past, regulate_reason, regulate_method, designation_domestic, designation_overseas, designation_organization, dictionary_id) "
animal_template += "VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
plant_template = "INSERT INTO Plant(shape_size, shape_stem, shape_leaf, shape_flower_description, shape_flower_color, shape_fruit, ecological_growth_period, ecological_bloom_period, ecological_habitat_domestic, ecological_habitat_overseas, introduction_origin, introduction_period, dictionary_id) "
plant_template += "VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"

# insert values
with open('csv/ecosystem_disruptor.csv', newline='') as csvfile:
  reader = csv.reader(csvfile, delimiter=',', quotechar='"')
  for idx, row in enumerate(reader):
    _, korean_name, english_name, kind = row
    with open(f'data/{kind}/{english_name}.json', encoding='utf-8') as json_file:
      json_data = json.load(json_file)
      korean_name = json_data['korean_name']
      english_name = json_data['english_name']
      summary = json_data['summary']
      kind = json_data['kind']
      image = json_data['image']
      curs.execute(dict_template, (korean_name, english_name, summary, kind, image))
      if kind != 'plant':
        shape = json_data['shape'] if json_data['shape'] != 'no data' else None
        ecological_habitat = json_data['ecological']['habitat'] if json_data['ecological']['habitat'] != 'no data' else None
        ecological_lifespan = json_data['ecological']['lifespan'] if json_data['ecological']['lifespan'] != 'no data' else None
        ecological_etc = json_data['ecological']['etc'] if json_data['ecological']['etc'] != 'no data' else None
        introduction_origin = json_data['introduction']['origin'] if json_data['introduction']['origin'] != 'no data' else None
        introduction_period = json_data['introduction']['period'] if json_data['introduction']['period'] != 'no data' else None
        introduction_purpose = json_data['introduction']['purpose'] if json_data['introduction']['purpose'] != 'no data' else None
        distribution = json_data['distribution'] if json_data['distribution'] != 'no data' else None
        effect_ecosystem = json_data['effect']['ecosystem'] if json_data['effect']['ecosystem'] != 'no data' else None
        effect_entity = json_data['effect']['entity'] if json_data['effect']['entity'] != 'no data' else None
        regulate_past = json_data['regulate']['past'] if json_data['regulate']['past'] != 'no data' else None
        regulate_reason = json_data['regulate']['reason'] if json_data['regulate']['reason'] != 'no data' else None
        regulate_method = json_data['regulate']['method'] if json_data['regulate']['method'] != 'no data' else None
        designation_domestic = json_data['designation']['domestic'] if json_data['designation']['domestic'] != 'no data' else None
        designation_overseas = json_data['designation']['overseas'] if json_data['designation']['overseas'] != 'no data' else None
        designation_organization = json_data['designation']['organization'] if json_data['designation']['organization'] != 'no data' else None
        curs.execute(animal_template, (shape, ecological_habitat, ecological_lifespan, ecological_etc, introduction_origin, introduction_period, introduction_purpose, distribution, effect_ecosystem, effect_entity, regulate_past, regulate_reason, regulate_method, designation_domestic, designation_overseas, designation_organization, idx+1))
      else:
        shape = json_data['shape']
        shape_size = shape['size'] if shape['size'] != 'no data' else None
        shape_stem = shape['stem'] if shape['stem'] != 'no data' else None
        shape_leaf = shape['leaf'] if shape['leaf'] != 'no data' else None
        flower = shape['flower']
        shape_flower_description = flower['description'] if flower['description'] != 'no data' else None
        shape_flower_color = flower['color'] if flower['color'] != 'no data' else None
        shape_fruit = shape['fruit'] if shape['fruit'] != 'no data' else None
        ecological = json_data['ecological']
        ecological_growth_period = ecological['growth_period'] if ecological['growth_period'] != 'no data' else None
        ecological_bloom_period = ecological['bloom_period'] if ecological['bloom_period'] != 'no data' else None
        habitat = ecological['habitat']
        ecological_habitat_domestic = habitat['domestic'] if habitat['domestic'] != 'no data' else None
        ecological_habitat_overseas = habitat['overseas'] if habitat['overseas'] != 'no data' else None
        introduction = json_data['introduction']
        introduction_origin = introduction['origin'] if introduction['origin'] != 'no data' else None
        introduction_period = introduction['period'] if introduction['period'] != 'no data' else None
        curs.execute(plant_template, (shape_size, shape_stem, shape_leaf, shape_flower_description, shape_flower_color, shape_fruit, ecological_growth_period, ecological_bloom_period, ecological_habitat_domestic, ecological_habitat_overseas, introduction_origin, introduction_period, idx+1))
      conn.commit()