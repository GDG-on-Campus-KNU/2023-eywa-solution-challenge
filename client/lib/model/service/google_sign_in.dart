import 'package:eywa_client/model/constants/key.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';

Future<String?> signInWithGoogle() async {
  final loginUrl = Uri.https(baseUrl, URLGoogleSignIn);
  final result = await FlutterWebAuth.authenticate(
    url: loginUrl.toString(),
    callbackUrlScheme: CALLBACK_SCHEME
  );
  return Uri.parse(result).queryParameters['JSESSIONID'];
}