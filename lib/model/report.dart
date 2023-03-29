import 'dart:convert';
import 'dart:io';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:http/http.dart' as http;
import 'package:http_parser/http_parser.dart';
import 'constants/key.dart';
import 'service/api.dart';
import 'package:eywa_client/model/register.dart';

class Report{
  Register registerInfo;
  String imagePath;
  int reportId;

  Report({
    required this.registerInfo,
    required this.imagePath,
    required this.reportId,
  });

  static Future<bool> POSTReport({
    required Report report
  }) async {

    var request = http.MultipartRequest('POST', Uri.https(baseUrl, URLReport));
    request.headers.addAll({
      "Cookie" : "JSESSIONID=${Get.find<UserController>().sessionId};",
    });

    request.files.add(http.MultipartFile.fromString(
        "reportRequestDto", jsonEncode({
        "latitude": double.parse(report.registerInfo.coor.latitude.toStringAsFixed(6)),
        "longitude": double.parse(report.registerInfo.coor.longitude.toStringAsFixed(6)),
        "dictionaryId": report.registerInfo.dictionaryId,
      }),
      contentType: MediaType.parse("application/json"),
    ));

    request.files.add(
      await http.MultipartFile.fromPath(
        'image',
        report.imagePath,
        contentType: MediaType('image', 'jpg'),
      ),
    );

    var response = await request.send();
    print("report status : ${response.statusCode}");

    switch(response.statusCode){
      case 200:
        return true;
      default:
        return false;
    }
  }

  //////////////////////////////////////////////////////////////////////////////[Get] reports
  static List<Report> ReportListFromJson(dynamic json){
    List<Report> reports = [];
    List<dynamic>.from(json).forEach((element) {
      Map<String, dynamic> e = Map<String, dynamic>.from(element);
      reports.add(Report(
        registerInfo: Register(
          coor: LatLng(e["latitude"], e["longitude"]),
          dictionaryId: Map<String, dynamic>.from(Map<String, dynamic>.from(e["dictionary"])["data"])["id"],
        ),
        imagePath: e["picture"],
        reportId: e["id"],
      ));
    });
    return reports;
  }

  static Future<List<Report>> GETReports() async {
    ApiResponse apiResponse = ApiResponse();

    try {
      final response = await http.get(
        Uri.https(baseUrl, URLReport),
        headers: {
          "cookie" : "JSESSIONID=${Get.find<UserController>().sessionId};",
        },
      );

      print(utf8.decode(response.bodyBytes));

      switch (response.statusCode) {
        case 200:
          return ReportListFromJson(json.decode(response.body));
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

    return [];
  }
}