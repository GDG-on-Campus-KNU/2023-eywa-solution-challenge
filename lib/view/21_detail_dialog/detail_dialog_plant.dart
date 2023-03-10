import 'dart:ui';

import 'package:eywa_client/model/field_guid_element.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

BackdropFilter DetailDialogPlant(BuildContext context, FieldGuideElementPlant element){
  return BackdropFilter(
    filter: ImageFilter.blur(sigmaX: 3, sigmaY: 3),
    child: Dialog(
      insetPadding: EdgeInsets.all(0),
      backgroundColor: Color(0x00000000),

      child: Stack(
        alignment: Alignment.center,
        children: [
          _background(),
          _detail(context, element),
          _image(element.image),
        ],
      ),
    ),
  );
}

Widget _background(){
  return Positioned(
    width: 390.w,
    height: 844.h,
    child: GestureDetector(
      onTap: (){
        Get.back();
      },
    )
  );
}

Widget _image(String image){
  return Positioned(
    bottom: 485.h,
    child: Container(
      width: 250.w,
      height: 250.w,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20.r),
        image: DecorationImage(
          image: NetworkImage(image),
          fit: BoxFit.cover,
        ),
      ),
    ),
  );
}

Widget _detail(BuildContext context, FieldGuideElementPlant element){
  return Positioned(
    bottom: 0,
    child: Container(
      width: 390.w,
      height: 610.h,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(20.r),
          topRight: Radius.circular(20.r),
        ),
        color: context.theme.primaryColor,
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(height: 134.h),
          Text(element.engName, style: TextStyle(
            fontSize: 25.sp,
            fontWeight: FontWeight.w700,
            color: context.theme.backgroundColor,
          )),
          SizedBox(height: 9.h),
          _cardSection(context, element),
        ],
      ),
    ),
  );
}

Widget _cardSection(BuildContext context, FieldGuideElementPlant element){
  return Container(
  );
}