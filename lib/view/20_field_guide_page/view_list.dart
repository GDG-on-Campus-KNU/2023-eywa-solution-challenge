import 'package:eywa_client/view/21_detail_dialog/detail_dialog_animal.dart';
import 'package:eywa_client/view/21_detail_dialog/detail_dialog_plant.dart';
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
      Get.dialog(
        DetailDialogPlant(context, element),
        barrierColor: Color(0x00000000),
      );
    },
    child: Container(
      width: 310.w,
      height: 100.h,
      margin: EdgeInsets.only(bottom: 17.h),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20.r),
        color: context.theme.primaryColor
      ),
      child: Row(
        children: [
          Container(
            width: 100.h,
            height: 100.h,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20.r),
              image: DecorationImage(
                image: NetworkImage(element.image),
                fit: BoxFit.cover,
              ),
            ),
          ),
          SizedBox(width: 11.w),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(element.engName, style: TextStyle(
                fontSize: 20.sp,
                fontWeight: FontWeight.w700,
                color: context.theme.backgroundColor,
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
          )
        ],
      ),
    ),
  );
}

Widget _listViewElementAnimal(BuildContext context, FieldGuideElementAnimal element) {
  return GestureDetector(
    onTap: (){
      Get.dialog(
        DetailDialogAnimal(context, element),
        barrierColor: Color(0x00000000),
      );
    },
    child: Container(
      width: 310.w,
      height: 100.h,
      margin: EdgeInsets.only(bottom: 17.h),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20.r),
        color: context.theme.primaryColor
      ),
      child: Row(
        children: [
          Container(
            width: 100.h,
            height: 100.h,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20.r),
              image: DecorationImage(
                image: NetworkImage(element.image),
                fit: BoxFit.cover,
              ),
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