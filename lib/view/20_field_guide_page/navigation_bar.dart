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
            _camera(context),
            _fieldGuide(context),
            _account(context),
          ],
        ),
      )
  );
}

////////////////////////////////////////////////////////////////////////////////FieldGuide Button
Widget _fieldGuide(BuildContext context) => Positioned(
  left: 56.w,
  child: GestureDetector(
    child: Image.asset("assets/icons/bag.png", width: 30.w,),
  ),
);

////////////////////////////////////////////////////////////////////////////////Camera Button
Widget _camera(BuildContext context) => Positioned(
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
      child: Image.asset("assets/icons/camera.png", width: 30.w,),
    ),
  ),
);

////////////////////////////////////////////////////////////////////////////////Account Button
Widget _account(BuildContext context) => Positioned(
  right: 56.w,
  child: GestureDetector(
    child: Image.asset("assets/icons/user.png", width: 30.w,),
  ),
);


Widget _background(BuildContext context) => Positioned(
  child: Container(
    width: 390.w,
    height: 55.h,
    color: context.theme.primaryColor,
  ),
);