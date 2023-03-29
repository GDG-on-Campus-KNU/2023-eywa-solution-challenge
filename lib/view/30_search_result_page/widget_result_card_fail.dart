import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:cross_file_image/cross_file_image.dart';

Widget resultCardFail(BuildContext context) => Positioned(
  top: 125.h,
  child: Container(
    width: 300.w,
    height: 600.h,
    decoration: BoxDecoration(
      color: context.theme.primaryColor,
      borderRadius: BorderRadius.circular(20.r),
    ),
    child: Column(
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        _image(),
        _text(context),
      ],
    ),
  ),
);

Widget _image() => Container(
  width: 300.w,
  height: 300.w,
  decoration: BoxDecoration(
    borderRadius: BorderRadius.circular(20.r),
    image: DecorationImage(
      image: XFileImage(Get.find<SearchPageViewController>().image!.value),
      fit: BoxFit.cover,
    ),
  ),
);

Widget _text(BuildContext context) => Container(
  width: 300.w,
  height: 539.h - 300.w,
  child: Column(
    mainAxisAlignment: MainAxisAlignment.center,
    children: [
      Text(
        "This is not",
        style: TextStyle(
          color: context.theme.backgroundColor,
          fontSize: 30.sp,
          fontWeight: FontWeight.w600,
        ),
      ),
      Text(
        "a invasive species",
        style: TextStyle(
          color: context.theme.backgroundColor,
          fontSize: 30.sp,
          fontWeight: FontWeight.w600,
        ),
      ),
    ],
  ),
);