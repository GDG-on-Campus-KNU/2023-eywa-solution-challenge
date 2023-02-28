import 'package:eywa_client/model/service/get_current_location.dart';
import 'package:eywa_client/model/service/permissions.dart';
import 'package:geolocator/geolocator.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class UserController extends GetxController {

  //////////////////////////////////////////////////////////////////////////////methods
  @override
  void onInit() {
    // TODO: implement onInit
    super.onInit();
    getPosition();
  }

  //////////////////////////////////////////////////////////////////////////////Token
  String? accessToken;

  //////////////////////////////////////////////////////////////////////////////userInformation

  ////location
  Rx<LatLng> curPosition = LatLng(35.8880789, 128.611526).obs;

  void getPosition() async {
    geolocatorPermission();
    Position curPos = await returnCurPosition();
    curPosition(LatLng(curPos.latitude, curPos.longitude));
  }
}