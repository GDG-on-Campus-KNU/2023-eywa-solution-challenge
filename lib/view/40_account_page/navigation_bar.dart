import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

Widget accountPageNavigationBar(BuildContext context){
  return Positioned(
      bottom: -11.h,
      child: Hero(
        tag: "navigationBar",
        child: Container(
          width: 390.w,
          height: 76.h,
          child: Stack(
            alignment: Alignment.center,
            children: [
              _background(context),
              _down(context),
            ],
          ),
        ),
      )
  );
}

////////////////////////////////////////////////////////////////////////////////Down
Widget _down(BuildContext context) => Positioned(
  top: 0,
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
        child: Image.asset("assets/icons/downArrow.png", width: 30.w,),
      ),
    ),
  ),
);

////////////////////////////////////////////////////////////////////////////////Background
Widget _background(BuildContext context) => Positioned(
  child: Container(
    width: 390.w,
    height: 55.h,
    color: context.theme.primaryColor,
  ),
);