import 'package:eywa_client/view_model/home_page_controller.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:get/get.dart';

Widget homeMap() {
  return Positioned(
    child: GoogleMap(
      zoomControlsEnabled: false,
      onMapCreated: Get.find<HomePageController>().onMapCreated,
      initialCameraPosition: CameraPosition(
        target: Get.find<UserController>().curPosition.value,
        zoom: 15,
      ),
      markers: Set<Marker>.from(Get.find<HomePageController>().markers.map(
        (e) => Marker(
          markerId: MarkerId(e.id.toString()),
          position: e.coor
        )
      ),
    )),
  );
}