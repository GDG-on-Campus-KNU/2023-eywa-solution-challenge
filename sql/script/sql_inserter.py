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
        shape = json_data['shape']
        ecological_habitat = json_data['ecological']['habitat']
        ecological_lifespan = json_data['ecological']['lifespan']
        ecological_etc = json_data['ecological']['etc']
        introduction_origin = json_data['introduction']['origin']
        introduction_period = json_data['introduction']['period']
        introduction_purpose = json_data['introduction']['purpose']
        distribution = json_data['distribution']
        effect_ecosystem = json_data['effect']['ecosystem']
        effect_entity = json_data['effect']['entity']
        regulate_past = json_data['regulate']['past']
        regulate_reason = json_data['regulate']['reason']
        regulate_method = json_data['regulate']['method']
        designation_domestic = json_data['designation']['domestic']
        designation_overseas = json_data['designation']['overseas']
        designation_organization = json_data['designation']['organization']
        curs.execute(animal_template, (shape, ecological_habitat, ecological_lifespan, ecological_etc, introduction_origin, introduction_period, introduction_purpose, distribution, effect_ecosystem, effect_entity, regulate_past, regulate_reason, regulate_method, designation_domestic, designation_overseas, designation_organization, idx+1))
      else:
        shape = json_data['shape']
        shape_size = shape['size']
        shape_stem = shape['stem']
        shape_leaf = shape['leaf']
        flower = shape['flower']
        shape_flower_description = flower['description']
        shape_flower_color = flower['color']
        shape_fruit = shape['fruit']
        ecological = json_data['ecological']
        ecological_growth_period = ecological['growth_period']
        ecological_bloom_period = ecological['bloom_period']
        habitat = ecological['habitat']
        ecological_habitat_domestic = habitat['domestic']
        ecological_habitat_overseas = habitat['overseas']
        introduction = json_data['introduction']
        introduction_origin = introduction['origin']
        introduction_period = introduction['period']
        curs.execute(plant_template, (shape_size, shape_stem, shape_leaf, shape_flower_description, shape_flower_color, shape_fruit, ecological_growth_period, ecological_bloom_period, ecological_habitat_domestic, ecological_habitat_overseas, introduction_origin, introduction_period, idx+1))
      conn.commit()