import 'dart:convert';
import 'dart:io';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'constants/key.dart';
import 'service/api.dart';

class FieldGuideElement{
  int id;
  String korName;
  String engName;
  String summary;
  String kind;
  String image;

  FieldGuideElement({
    required this.id,
    required this.korName,
    required this.engName,
    required this.summary,
    required this.kind,
    required this.image,
  });
}

class FieldGuideElementAnimal extends FieldGuideElement{
  String shape;
  Map<String, String> ecological;
  Map<String, String> introduction;
  String? distribution;
  Map<String, String> effect;
  Map<String, String> regulate;
  Map<String, String> designation;

  FieldGuideElementAnimal({
    required int id,
    required String korName,
    required String engName,
    required String summary,
    required String kind,
    required String image,
    required this.shape,
    required this.ecological,
    required this.introduction,
    required this.distribution,
    required this.effect,
    required this.regulate,
    required this.designation,
  }) : super(
    id: id,
    korName: korName,
    engName: engName,
    summary: summary,
    kind: kind,
    image: image,
  );
}

class FieldGuideElementPlant extends FieldGuideElement{
  Map<String, String> shape;
  Map<String, String> ecological;
  Map<String, String> introduction;

  FieldGuideElementPlant(
  {
    required int id,
    required String korName,
    required String engName,
    required String summary,
    required String kind,
    required String image,
    required this.shape,
    required this.ecological,
    required this.introduction,
  }) : super(
    id: id,
    korName: korName,
    engName: engName,
    summary: summary,
    kind: kind,
    image: image,
  );
}

class FieldGuideElementPlantAndAnimal{
  List<FieldGuideElementPlant> plants;
  List<FieldGuideElementAnimal> animals;

  FieldGuideElementPlantAndAnimal({
    required this.plants,
    required this.animals,
  });

  FieldGuideElementPlantAndAnimal.nullInit({
    this.plants = const <FieldGuideElementPlant>[],
    this.animals = const <FieldGuideElementAnimal>[],
  });

  static FieldGuideElementPlantAndAnimal FromJsonFieldGuideElementList(dynamic json){
    FieldGuideElementPlantAndAnimal newFieldGuideElementPlantAndAnimal = FieldGuideElementPlantAndAnimal(
      plants: <FieldGuideElementPlant>[],
      animals: <FieldGuideElementAnimal>[],
    );

    List<dynamic>.from(json).forEach((element) {
      Map<String, dynamic> e = Map<String, dynamic>.from(element);
      if(e["kind"] == "plant"){
        newFieldGuideElementPlantAndAnimal.plants.add(
          FieldGuideElementPlant(
            id: e["id"],
            korName: e["korName"] ?? "null",
            engName: e["engName"] ?? "null",
            summary: e["summary"] ?? "null",
            kind: e["kind"] ?? "null",
            image: e["image"] ?? "null",
            shape: Map<String, String>.from(e["shape"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
            ecological: Map<String, String>.from(e["ecological"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
            introduction: Map<String, String>.from(e["introduction"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
          )
        );
      }
      else{
        newFieldGuideElementPlantAndAnimal.animals.add(
          FieldGuideElementAnimal(
            id: e["id"],
            korName: e["korName"] ?? "null",
            engName: e["engName"] ?? "null",
            summary: e["summary"] ?? "null",
            kind: e["kind"] ?? "null",
            image: e["image"] ?? "null",
            shape: e["shape"] ?? "null",
            ecological: Map<String, String>.from(e["ecological"] == null ? {} : e["ecological"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
            introduction: Map<String, String>.from(e["introduction"] == null ? {} : e["introduction"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
            distribution: e["distribution"] ?? "null",
            effect: Map<String, String>.from(e["effect"] == null ? {} : e["effect"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
            regulate: Map<String, String>.from(e["regulate"] == null ? {} : e["regulate"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
            designation: Map<String, String>.from(e["designation"] == null ? {} : e["designation"].map((key, dynamic value) => MapEntry(key, value ?? "null"))),
          )
        );
      }
    });

    return newFieldGuideElementPlantAndAnimal;
  }

  static Future<FieldGuideElementPlantAndAnimal> GETFieldGuideList() async {
    ApiResponse apiResponse = ApiResponse();

    try {
      final response = await http.get(
          Uri.parse("http://" + baseUrl + '/' + URLGetFieldGuideElement),
      );

      print(utf8.decode(response.bodyBytes));

      switch (response.statusCode) {
        case 200:
          return FieldGuideElementPlantAndAnimal.FromJsonFieldGuideElementList(json.decode(utf8.decode(response.bodyBytes)));
        case 401:
          apiResponse.apiError = ApiError.fromJson(json.decode(response.body));
          break;
        default:
          apiResponse.apiError = ApiError.fromJson(json.decode(response.body));
          break;
      }
    } on SocketException {
      apiResponse.apiError = ApiError(error: "Server error. Please retry");
    }

    return FieldGuideElementPlantAndAnimal.nullInit();
  }
}