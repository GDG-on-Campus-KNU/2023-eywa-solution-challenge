import 'package:eywa_client/model/member.dart';
import 'package:eywa_client/model/service/get_current_location.dart';
import 'package:eywa_client/model/service/google_sign_in.dart';
import 'package:eywa_client/model/service/permissions.dart';
import 'package:eywa_client/model/service/try_auto_sign_in.dart';
import 'package:geolocator/geolocator.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class UserController extends GetxController {

  void signInSuccess(){
    getPosition();
    getMember();
  }

  //////////////////////////////////////////////////////////////////////////////Session Id
  String sessionId = "";
  Future<bool> googleLogin() async {
    String? newSessionId = await signInWithGoogle();
    if(newSessionId != null && await trySignIn(newSessionId)){
      writeSessionIdToLocalStorage(newSessionId);
      return true;
    }
    return false;
  }

  Future<bool> tryAutoSignIn() async {
    String? newSessionId = await getSessionIdFromLocalStorage();
    if(newSessionId != null && await trySignIn(newSessionId)){
      return true;
    }
    return false;
  }

  Future<bool> trySignIn(String sessionId) async {
    if(await tryLogInToServer(sessionId)){
      this.sessionId = sessionId;
      print(sessionId);
      return true;
    }
    return false;
  }


  //////////////////////////////////////////////////////////////////////////////userInformation

  ////location
  Rx<LatLng> curPosition = LatLng(35.8880789, 128.611526).obs;

  void getPosition() async {
    try {
      Position curPos = await returnCurPosition();
      curPosition(LatLng(curPos.latitude, curPos.longitude));
    } catch (e) {
      geolocatorPermission();
    }
  }

  ////Member
  Rx<Member> member = Member.nullInit().obs;

  void getMember() async {
    member(await Member.GETMember(sessionId));
  }

  int animalsFound = 12;
  int plantsFound = 8;

}