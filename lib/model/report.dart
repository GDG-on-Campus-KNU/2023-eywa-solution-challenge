import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';
import 'dart:ui';
import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'package:image_picker/image_picker.dart';
import 'constants/key.dart';
import 'service/api.dart';
import 'package:eywa_client/model/register.dart';
import 'package:image/image.dart' as img;

class Report{
  Register registerInfo;
  String ImagePath;

  Report({
    required this.registerInfo,
    required this.ImagePath,
  });

  static Future<bool> POSTReport({
    required Report report
  }) async {

    var request = http.MultipartRequest('POST', Uri.https(baseUrl, URLReport));
    request.headers.addAll({
      "Cookie" : "JSESSIONID=${Get.find<UserController>().sessionId};",
      // "Content-Type": "application/json; image/jpg",
    });

    request.fields["reportRequestDto"] = jsonEncode({
      "latitude": report.registerInfo.coor.latitude,
      "longitude": report.registerInfo.coor.longitude,
      "dictionaryId": report.registerInfo.dictionaryId,
    });

    request.files.add(
      await http.MultipartFile.fromPath(
        'image',
        report.ImagePath,
      ),
    );

    var response = await request.send();
    print(response.statusCode);

    return true;
  }
}