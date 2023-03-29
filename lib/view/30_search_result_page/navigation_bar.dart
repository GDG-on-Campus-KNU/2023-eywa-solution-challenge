import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

Widget searchPageNavigationBar(BuildContext context){
  return Positioned(
      bottom: 0,
      child: Container(
        width: 390.w,
        height: 114.h,
        child: Stack(
          alignment: Alignment.center,
          children: [
            _background(context),
            _reCamera(context),
            _cancel(context),
          ],
        ),
      )
  );
}

////////////////////////////////////////////////////////////////////////////////FieldGuide Button
Widget _cancel(BuildContext context) => Positioned(
  left: 56.w,
  bottom: 12.h,
  child: GestureDetector(
    onTap: (){
      Get.offAndToNamed("/home");
    },
    child: Image.asset("assets/icons/cancel.png", height: 30.h,),
  ),
);

////////////////////////////////////////////////////////////////////////////////Camera Button
Widget _reCamera(BuildContext context) => Positioned(
  top: 0,
  child: GestureDetector(
    onTap: (){
      Get.find<SearchPageViewController>().takePhoto(true).then((value){
        if(value){
          Get.toNamed("/search_result");
        }
      });
    },
    child: Container(
      width: 76.h,
      height: 76.h,
      decoration: BoxDecoration(
        color: context.theme.backgroundColor,
        shape: BoxShape.circle,
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.25),
            spreadRadius: 2,
            blurRadius: 7,
            offset: Offset(0, 4), // changes position of shadow
          ),
        ],
      ),
      alignment: Alignment.center,
      padding: EdgeInsets.only(bottom: 6.h),
      child: GestureDetector(
        child: Image.asset("assets/icons/camera_switch.png", width: 30.w,),
      ),
    ),
  ),
);

////////////////////////////////////////////////////////////////////////////////Background
Widget _background(BuildContext context) => Positioned(
  bottom: 0,
  child: Container(
    width: 390.w,
    height: 55.h,
    color: context.theme.primaryColor,
  ),
);