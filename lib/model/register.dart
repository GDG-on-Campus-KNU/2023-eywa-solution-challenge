import 'dart:convert';
import 'dart:io';
import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'constants/key.dart';
import 'service/api.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class Register{
  LatLng coor;
  int dictionaryId;

  Register({
    required this.coor,
    required this.dictionaryId,
  });

  static Future<bool> POSTRegister({
    required Register register
  }) async {
  ApiResponse apiResponse = ApiResponse();

    try {
      final response = await http.post(
        Uri.https(baseUrl, URLRegister),
        headers: {
          "cookie" : "JSESSIONID=${Get.find<UserController>().sessionId};",
          "Content-Type": "application/json",
        },
        body: jsonEncode({
          "latitude": double.parse((register.coor.latitude + 1).toStringAsFixed(6)),
          "longitude": double.parse(register.coor.longitude.toStringAsFixed(6)),
          "dictionaryId": register.dictionaryId,
        }),
      );

      print("register status : ${response.statusCode}");

      switch (response.statusCode) {
        case 200:
          Get.find<FieldGuidePageController>().plantElements.firstWhereOrNull((element) => element.id == register.dictionaryId)?.registered = true;
          Get.find<FieldGuidePageController>().animalElements.firstWhereOrNull((element) => element.id == register.dictionaryId)?.registered = true;
          return true;
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

    return false;
  }
}