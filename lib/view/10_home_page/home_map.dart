import 'package:cached_network_image/cached_network_image.dart';
import 'package:custom_map_markers/custom_map_markers.dart';
import 'package:eywa_client/model/field_guid_element.dart';
import 'package:eywa_client/view/common_widgets/detail_animal_page.dart';
import 'package:eywa_client/view/common_widgets/detail_plant_page.dart';
import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:eywa_client/view_model/home_page_controller.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:get/get.dart';

Widget homeMap(BuildContext context) {
  return Positioned(
    child: Obx(() => CustomGoogleMapMarkerBuilder(
      screenshotDelay: Duration(milliseconds: 3000),
      customMarkers: List<MarkerData>.from(Get.find<HomePageController>().reports.map((e) {
        return MarkerData(
          marker: Marker(
              markerId: MarkerId(e.reportId.toString()),
              position: e.registerInfo.coor,
              onTap: (){
                bool ifPlant = false;
                Get.find<FieldGuidePageController>().plantElements.forEach((element) {
                  if(element.id == e.registerInfo.dictionaryId){
                    ifPlant = true;
                  }
                });

                if(ifPlant){
                  FieldGuideElementPlant element = Get.find<FieldGuidePageController>().plantElements.firstWhere((element) => element.id == e.registerInfo.dictionaryId);
                  Get.to(DetailPlant(element: element, imagePath: e.imagePath,), opaque: false, transition: Transition.downToUp);
                }
                else{
                  FieldGuideElementAnimal element = Get.find<FieldGuidePageController>().animalElements.firstWhere((element) => element.id == e.registerInfo.dictionaryId);
                  Get.to(DetailAnimal(element: element, imagePath: e.imagePath,), opaque: false, transition: Transition.downToUp);
                }
              }
          ),
          child: Stack(
              alignment: Alignment.center,
              children:[
                Positioned(
                  child: Image.asset("assets/icons/marker.png", width: 49.w,),
                ),
                Positioned(
                    top: 2.w,
                    child: Container(
                      width: 45.w,
                      height: 45.w,
                      child: ClipRRect(
                        borderRadius: BorderRadius.circular(50.r),
                        // child: Image.network(e.imagePath, fit: BoxFit.cover,),
                        child: CachedNetworkImage(
                          placeholder: (context, url) => Container(
                            child: Container(
                              width: 10.w,
                              height: 10.w,
                              color: Colors.transparent,
                              alignment: Alignment.center,
                              child: CircularProgressIndicator(
                                color: context.theme.primaryColorDark,
                              ),
                            ),
                          ),
                          errorWidget: (context, url, error) => Icon(
                            Icons.error,
                            color: context.theme.primaryColorDark,
                          ),
                          imageUrl: e.imagePath,
                          fit: BoxFit.cover,
                        ),
                      ),
                    )
                ),
              ]
          ),
        );
      }).toList()),
      builder: (BuildContext context, Set<Marker>? markers){
        return GoogleMap(
          zoomControlsEnabled: false,
          onMapCreated: Get.find<HomePageController>().onMapCreated,
          initialCameraPosition: CameraPosition(
            target: Get.find<UserController>().curPosition.value,
            zoom: 15,
          ),
          markers: markers ?? <Marker>{},
        );
      }
    )),
  );
}