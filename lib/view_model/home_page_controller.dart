import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class HomePageController extends GetxController {

  //////////////////////////////////////////////////////////////////////////////Map
  late GoogleMapController mapController;
  Set<_tempMarker> markers = {
    _tempMarker(id: 0, image: 'assets/images/eywa.png', coor: LatLng(35.8887, 128.6119)),
    _tempMarker(id: 1, image: 'assets/images/eywa.png', coor: LatLng(35.8881, 128.6094)),
    _tempMarker(id: 2, image: 'assets/images/eywa.png', coor: LatLng(35.8901, 128.6119)),
  };

  void onMapCreated(GoogleMapController controller) {
    mapController = controller;
  }

  void updateCurCamPosition(LatLng newPosition){
    mapController.animateCamera(
      CameraUpdate.newCameraPosition(
        CameraPosition(
          target: newPosition,
        )
      )
    );
  }
}

class _tempMarker{
  int id;
  String image;
  LatLng coor;
  _tempMarker({required this.id, required this.image, required this.coor});
}