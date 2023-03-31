import 'package:eywa_client/model/constants/key.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'api.dart';

Future<bool> tryLogInToServer(String sessionId) async {
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

Future<String?> getSessionIdFromLocalStorage() async {
  const storage = FlutterSecureStorage();
  return await storage.read(key: sessionIdKey);
}

void writeSessionIdToLocalStorage(String sessionId) async {
  const storage = FlutterSecureStorage();
  storage.write(key: sessionIdKey, value: sessionId);
}