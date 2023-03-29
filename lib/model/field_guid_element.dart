import 'dart:convert';
import 'dart:io';
import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:eywa_client/view_model/user_controller.dart';
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
  bool registered;

  FieldGuideElement({
    required this.id,
    required this.korName,
    required this.engName,
    required this.summary,
    required this.kind,
    required this.image,
    required this.registered,
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
    required bool registered,
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
    registered: registered,
  );

  //////////////////////////////////////////////////////////////////////////////
  static FieldGuideElementAnimal? fieldGuideElementAnimalFromId(int dictionaryId){
    for (var element in Get.find<FieldGuidePageController>().animalElements) {
      if(element.id == dictionaryId) {
        return element;
      }
    }
    return null;
  }

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
    required bool registered,
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
    registered: registered,
  );

  //////////////////////////////////////////////////////////////////////////////
  static FieldGuideElementPlant? fieldGuideElementPlantFromId(int dictionaryId){
    for (var element in Get.find<FieldGuidePageController>().plantElements) {
      if(element.id == dictionaryId) {
        return element;
      }
    }
    return null;
  }
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
      Map<String, dynamic> e = Map<String, dynamic>.from(Map<String, dynamic>.from(element)["data"]);
      bool registered = Map<String, dynamic>.from(element)["register"] != null ? true : false;
      if(e["kind"] == "plant"){
        newFieldGuideElementPlantAndAnimal.plants.add(
          FieldGuideElementPlant(
            id: e["id"],
            korName: e["koreanName"] ?? "no data",
            engName: e["englishName"] ?? "no data",
            summary: e["summary"] ?? "no data",
            kind: e["kind"] ?? "no data",
            image: e["image"] ?? "no data",
            shape: Map<String, String>.from(e["shape"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            ecological: Map<String, String>.from(e["ecological"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            introduction: Map<String, String>.from(e["introduction"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            registered: registered,
          )
        );
      }
      else{
        newFieldGuideElementPlantAndAnimal.animals.add(
          FieldGuideElementAnimal(
            id: e["id"],
            korName: e["koreanName"] ?? "no data",
            engName: e["englishName"] ?? "no data",
            summary: e["summary"] ?? "no data",
            kind: e["kind"] ?? "no data",
            image: e["image"] ?? "no data",
            shape: e["shape"] ?? "no data",
            ecological: Map<String, String>.from(e["ecological"] == null ? {} : e["ecological"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            introduction: Map<String, String>.from(e["introduction"] == null ? {} : e["introduction"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            distribution: e["distribution"] ?? "no data",
            effect: Map<String, String>.from(e["effect"] == null ? {} : e["effect"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            regulate: Map<String, String>.from(e["regulate"] == null ? {} : e["regulate"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            designation: Map<String, String>.from(e["designation"] == null ? {} : e["designation"].map((key, dynamic value) => MapEntry(key, value ?? "no data"))),
            registered: registered,
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
        Uri.https(baseUrl, URLGetFieldGuideElement),
        headers: {
          "cookie" : "JSESSIONID=${Get.find<UserController>().sessionId}"
        },
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