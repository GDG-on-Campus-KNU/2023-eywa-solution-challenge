import 'package:eywa_client/model/member.dart';
import 'package:eywa_client/model/service/get_current_location.dart';
import 'package:eywa_client/model/service/permissions.dart';
import 'package:geolocator/geolocator.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class UserController extends GetxController {

  //////////////////////////////////////////////////////////////////////////////methods
  @override
  void onInit() {
    super.onInit();
    getPosition();
    getMember();
  }

  //////////////////////////////////////////////////////////////////////////////Token
  String sessionId = "FA8D3EEE6926EAF827256F1654F27CEE";

  //////////////////////////////////////////////////////////////////////////////userInformation

  ////location
  Rx<LatLng> curPosition = LatLng(35.8880789, 128.611526).obs;

  void getPosition() async {
    geolocatorPermission();
    Position curPos = await returnCurPosition();
    curPosition(LatLng(curPos.latitude, curPos.longitude));
  }

  ////Member
  Member member = Member.nullInit();

  void getMember() async{
    member = await Member.GETMember(sessionId);
  }

  int animalsFound = 12;
  int plantsFound = 8;

}