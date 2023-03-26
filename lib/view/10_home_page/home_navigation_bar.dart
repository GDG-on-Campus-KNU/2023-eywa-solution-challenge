import 'package:eywa_client/model/service/alien_species_descriminate.dart';
import 'package:eywa_client/view/10_home_page/dialog_camera_select.dart';
import 'package:eywa_client/view/40_account_page/account_page_dialog.dart';
import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

Widget homeNavigationBar(BuildContext context){
  return Positioned(
    bottom: -11.h,
    child: Hero(
      tag: "navigationBar",
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
      ),
    ),
  );
}

////////////////////////////////////////////////////////////////////////////////FieldGuide Button
Widget _fieldGuide(BuildContext context) => Positioned(
  left: 56.w,
  child: GestureDetector(
    onTap: () => Get.toNamed("/field_guide"),
    child: Image.asset("assets/icons/bag.png", width: 30.w,),
  ),
);

////////////////////////////////////////////////////////////////////////////////Camera Button
Widget _camera(BuildContext context) => Positioned(
  child: GestureDetector(
    onTap: (){
      Get.dialog(DialogCameraSelect(), barrierColor: Color(0x00000000));
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
      child: Image.asset("assets/icons/camera.png", width: 30.w,),
    ),
  ),
);

////////////////////////////////////////////////////////////////////////////////Account Button
Widget _account(BuildContext context) => Positioned(
  right: 56.w,
  child: GestureDetector(
    onTap: (){
      Get.dialog(AccountPageDialog(context), barrierColor: Color(0x00000000));
    },
    child: Image.asset("assets/icons/user.png", width: 30.w,),
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