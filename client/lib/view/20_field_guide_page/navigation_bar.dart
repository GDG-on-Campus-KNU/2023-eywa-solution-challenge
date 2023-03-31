import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

Widget fieldGuideNavigationBar(BuildContext context){
  return Positioned(
      bottom: -11.h,
      child: Container(
        width: 390.w,
        height: 76.h,
        decoration: BoxDecoration(
          boxShadow: [
            BoxShadow(
              color: Colors.black.withOpacity(0.25),
              spreadRadius: 2,
              blurRadius: 7,
              offset: Offset(0, 4), // changes position of shadow
            ),
          ],
        ),
        child: Stack(
          alignment: Alignment.center,
          children: [
            _background(context),
            _down(context),
            _plant(context),
            _animal(context),
          ],
        ),
      )
  );
}

////////////////////////////////////////////////////////////////////////////////FieldGuide Button
Widget _plant(BuildContext context) => Positioned(
  left: 56.w,
  child: GestureDetector(
    onTap: (){
      Get.find<FieldGuidePageController>().navigationChange(true);
    },
    child: Image.asset("assets/icons/plant.png", width: 30.w,),
  ),
);

////////////////////////////////////////////////////////////////////////////////Camera Button
Widget _down(BuildContext context) => Positioned(
  child: GestureDetector(
    onTap: (){
      Get.back();
    },
    child: Container(
      width: 76.h,
      height: 76.h,
      decoration: BoxDecoration(
        color: context.theme.backgroundColor,
        shape: BoxShape.circle,
      ),
      alignment: Alignment.center,
      padding: EdgeInsets.only(bottom: 6.h),
      child: GestureDetector(
        child: Image.asset("assets/icons/downArrow.png", width: 30.w,),
      ),
    ),
  ),
);

////////////////////////////////////////////////////////////////////////////////Account Button
Widget _animal(BuildContext context) => Positioned(
  right: 56.w,
  child: GestureDetector(
    onTap: (){
      Get.find<FieldGuidePageController>().navigationChange(false);
    },
    child: Image.asset("assets/icons/animal.png", width: 30.w,),
  ),
);


Widget _background(BuildContext context) => Positioned(
  child: Container(
    width: 390.w,
    height: 55.h,
    color: context.theme.primaryColor,
  ),
);