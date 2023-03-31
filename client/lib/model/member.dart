import 'dart:convert';
import 'dart:io';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'constants/key.dart';
import 'service/api.dart';

class Member{

  String name;
  String email;
  int exp;
  String profileImage;
  int level;

  Member({
    required this.name,
    required this.email,
    required this.exp,
    required this.profileImage,
    required this.level,
  });

  static Member FromJson(dynamic json) {
    return Member(
      name: json['name'],
      email: json['email'],
      exp: json['exp'] % 100,
      profileImage: json['picture'],
      level: json['level'],
    );
  }

  static Member nullInit() {
    return Member(
      name: "",
      email: "",
      exp: 0,
      profileImage: "",
      level: 0,
    );
  }

  static Future<Member> GETMember(String sessionId) async {
    ApiResponse apiResponse = ApiResponse();

    try {
      final response = await http.get(
        Uri.https(baseUrl, '/$URLGetMemberInfo'),
        headers: {
          "cookie" : "JSESSIONID=$sessionId"
        },
      );

      print(utf8.decode(response.bodyBytes));

      switch (response.statusCode) {
        case 200:
          return Member.FromJson(json.decode(utf8.decode(response.bodyBytes)));
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

    return Member.nullInit();
  }
}