import 'package:cross_file_image/cross_file_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import 'package:eywa_client/model/field_guid_element.dart';
import 'package:eywa_client/view_model/search_page_view_controller.dart';

Widget resultCardSuccess(BuildContext context, String imagePath, FieldGuideElement element) => Positioned(
  top: 100.h,
  child: Container(
    width: 300.w,
    height: 600.h,
    decoration: BoxDecoration(
      color: context.theme.primaryColor,
      borderRadius: BorderRadius.circular(20.r),
    ),
    child: Column(
      children: [
        _image(imagePath),
        Container(
          height: 600.h - 300.w,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              _name(context, element.engName),
              _description(context, element),
              _button(context),
            ],
          ),
        )
      ],
    ),
  ),
);

Widget _image(String imagePath) => Container(
  width: 300.w,
  height: 300.w,
  decoration: BoxDecoration(
    borderRadius: BorderRadius.circular(20.r),
    image: DecorationImage(
      image: XFileImage(Get.find<SearchPageViewController>().image!),
      fit: BoxFit.cover,
    ),
  ),
);

Widget _name(BuildContext context, String name) => Text(
  name,
  style: TextStyle(
    color: context.theme.backgroundColor,
    fontSize: 25.sp,
    fontWeight: FontWeight.w600,
    shadows: [
      Shadow(
        color: Colors.black.withOpacity(0.25),
        blurRadius: 7,
        offset: Offset(0, 4), // changes position of shadow
      ),
    ],
  ),
);

Widget _description(BuildContext context, FieldGuideElement element) => Container(
  width: 269.w,
  height: 105.h,
  padding: EdgeInsets.symmetric(horizontal: 22.w, vertical: 8.h),
  decoration: BoxDecoration(
    color: context.theme.backgroundColor,
    borderRadius: BorderRadius.circular(20.r),
    boxShadow: [
      BoxShadow(
        color: Colors.black.withOpacity(0.25),
        spreadRadius: 2,
        blurRadius: 7,
        offset: Offset(0, 4), // changes position of shadow
      ),
    ],
  ),
  child: Column(
    mainAxisAlignment: MainAxisAlignment.spaceBetween,
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      Text(element.korName,style: TextStyle(
        color: context.theme.primaryColor,
        fontSize: 15.sp,
        fontWeight: FontWeight.w600,
      ),),
      Text(element.kind,style: TextStyle(
        color: context.theme.primaryColor,
        fontSize: 15.sp,
        fontWeight: FontWeight.w600,
      ),),
      Text(element.summary,style: TextStyle(
        color: context.theme.primaryColor,
        fontSize: 15.sp,
        fontWeight: FontWeight.w600,
      ),),
    ],
  )
);

Widget _button(BuildContext context) => Container(
  width: 300.w,
  height: 38.h,
  alignment: Alignment.center,
  child: Row(
    children: [
      GestureDetector(
        child: Container(
          width: 148.5.w,
          height: 38.h,
          alignment: Alignment.center,
          decoration: BoxDecoration(
            color: context.theme.primaryColorDark,
            borderRadius: BorderRadius.only(
              bottomLeft: Radius.circular(20.r),
            ),
          ),
          child: Text("report", style: TextStyle(
            color: context.theme.backgroundColor,
            fontSize: 15.sp,
            fontWeight: FontWeight.w600,
          ),),
        ),
      ),
      Container(
        width: 3.w,
        height: 38.h,
        color: context.theme.backgroundColor,
      ),
      GestureDetector(
        child: Container(
          width: 148.5.w,
          height: 38.h,
          alignment: Alignment.center,
          decoration: BoxDecoration(
            color: context.theme.primaryColorDark,
            borderRadius: BorderRadius.only(
              bottomRight: Radius.circular(20.r),
            ),
          ),
          child: Text("details", style: TextStyle(
            color: context.theme.backgroundColor,
            fontSize: 15.sp,
            fontWeight: FontWeight.w600,
          ),),
        ),
      ),
    ],
  )
);