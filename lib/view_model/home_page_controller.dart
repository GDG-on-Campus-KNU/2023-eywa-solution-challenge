import 'package:eywa_client/model/report.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class HomePageController extends GetxController {

  @override
  void onInit() {
    super.onInit();
    getReports();
  }

  //////////////////////////////////////////////////////////////////////////////Map
  late GoogleMapController mapController;

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

  //////////////////////////////////////////////////////////////////////////////Reports
  RxList<Report> reports = <Report>[].obs;

  void getReports() async {
    reports(
      await Report.GETReports()
    );
  }
}