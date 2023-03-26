import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

class DialogCameraSelect extends StatelessWidget {
  const DialogCameraSelect({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Dialog(
      backgroundColor: Colors.transparent,
      elevation: 0,
      insetPadding: EdgeInsets.zero,
      child: Stack(
        alignment: Alignment.center,
        children: [
          _background(),
          _camera(context),
          _gallery(context),
        ],
      ),
    );
  }
}

Widget _background() => Positioned(
  width: 390.w,
  height: 844.h,
  child: Container(
    color: Colors.transparent,
    child: GestureDetector(
      onTap: () {
        Get.back();
      },
    ),
  ),
);

Widget _camera(BuildContext context) => Positioned(
  bottom: -11.h,
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
      ),
      alignment: Alignment.center,
      padding: EdgeInsets.only(bottom: 6.h),
      child: Image.asset("assets/icons/camera.png", width: 30.w,),
    ),
  ),
);

Widget _gallery(BuildContext context) => Positioned(
  bottom: 86.h,
  child: GestureDetector(
    onTap: (){
      Get.find<SearchPageViewController>().takePhoto(false).then((value){
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
      ),
      alignment: Alignment.center,
      child: Image.asset("assets/icons/gallary.png", width: 30.w,),
    ),
  ),
);