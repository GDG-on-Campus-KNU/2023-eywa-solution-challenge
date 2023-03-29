import "package:cached_network_image/cached_network_image.dart";
import 'package:eywa_client/view/common_widgets/detail_animal_page.dart';
import "package:eywa_client/view/common_widgets/detail_plant_page.dart";
import "package:flutter/material.dart";
import "package:flutter_screenutil/flutter_screenutil.dart";
import "package:get/get.dart";

import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:eywa_client/model/field_guid_element.dart';


Widget fieldGuideListView(BuildContext context){
  return Container(
    alignment: Alignment.center,
    child: ListView(
      padding: EdgeInsets.symmetric(horizontal: 40.w, vertical: 29.h),
      children: Get.find<FieldGuidePageController>().ifPlant.value ?
      Get.find<FieldGuidePageController>().plantElements.map(
        (e) => _listViewElementPlant(context, e)
      ).toList()
      : Get.find<FieldGuidePageController>().animalElements.map(
        (e) => _listViewElementAnimal(context, e)
      ).toList(),
    )
  );
}

Widget _listViewElementPlant(BuildContext context, FieldGuideElementPlant element) {
  return GestureDetector(
    onTap: (){
      Get.to(DetailPlant(element: element, imagePath: element.image,), opaque: false, transition: Transition.downToUp);
    },
    child: Container(
      width: 310.w,
      height: 100.h,
      margin: EdgeInsets.only(bottom: 17.h),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20.r),
        color: context.theme.primaryColor,
      ),
      child: Row(
        children: [
          Hero(
            tag: element.id.toString(),
            child: Container(
              width: 100.h,
              height: 100.h,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20.r),
              ),
              child: ClipRRect(
                borderRadius: BorderRadius.circular(20.r),
                child: ColorFiltered(
                  colorFilter: element.registered ?
                    ColorFilter.mode(Colors.transparent, BlendMode.saturation)
                    : ColorFilter.mode(Colors.grey, BlendMode.saturation),
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
                    imageUrl: element.image,
                    fit: BoxFit.cover,
                  ),
                ),
              )
            ),
          ),
          SizedBox(width: 11.w),
          Container(
            width: 300.w - 100.h - 11.w,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(element.engName, style: TextStyle(
                  fontSize: 20.sp,
                  fontWeight: FontWeight.w700,
                  color: context.theme.backgroundColor,
                  overflow: TextOverflow.ellipsis,
                )),
                Text(element.korName, style: TextStyle(
                  fontSize: 15.sp,
                  fontWeight: FontWeight.w500,
                  color: context.theme.backgroundColor,
                )),
                Text(element.kind, style: TextStyle(
                  fontSize: 15.sp,
                  fontWeight: FontWeight.w500,
                  color: context.theme.backgroundColor,
                )),
              ],
            ),
          )
        ],
      ),
    ),
  );
}

Widget _listViewElementAnimal(BuildContext context, FieldGuideElementAnimal element) {
  return GestureDetector(
    onTap: (){
      Get.to(DetailAnimal(element: element, imagePath: element.image,), opaque: false, transition: Transition.downToUp);
    },
    child: Container(
      width: 310.w,
      height: 100.h,
      margin: EdgeInsets.only(bottom: 17.h),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20.r),
        color: context.theme.primaryColor,
      ),
      child: Row(
        children: [
          Hero(
            tag: element.id.toString(),
            child: Container(
              width: 100.h,
              height: 100.h,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20.r),
              ),
              child: ClipRRect(
                borderRadius: BorderRadius.circular(20.r),
                child: ColorFiltered(
                  colorFilter: element.registered ?
                    ColorFilter.mode(Colors.transparent, BlendMode.saturation)
                    : ColorFilter.mode(Colors.grey, BlendMode.saturation),
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
                    imageUrl: element.image,
                    fit: BoxFit.cover,
                  ),
                ),
              )
            ),
          ),
          SizedBox(width: 11.w),
          Container(
            width: 300.w - 100.h - 11.w,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(element.engName, style: TextStyle(
                  fontSize: 20.sp,
                  fontWeight: FontWeight.w700,
                  color: context.theme.backgroundColor,
                  overflow: TextOverflow.ellipsis,
                )),
                Text(element.korName, style: TextStyle(
                  fontSize: 15.sp,
                  fontWeight: FontWeight.w500,
                  color: context.theme.backgroundColor,
                )),
                Text(element.kind, style: TextStyle(
                  fontSize: 15.sp,
                  fontWeight: FontWeight.w500,
                  color: context.theme.backgroundColor,
                )),
              ],
            ),
          )
        ],
      ),
    ),
  );
}