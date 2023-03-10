import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

Widget fieldGuideAppBar(BuildContext context){
  return Positioned(
    top: 0,
    child: Container(
      width: 390.w,
      height: 60.h,
      decoration: BoxDecoration(
        color: context.theme.primaryColor,
        borderRadius: BorderRadius.only(
          bottomLeft: Radius.circular(20.r),
          bottomRight: Radius.circular(20.r)
        ),
        boxShadow: [
          BoxShadow(
            color: Colors.black.withOpacity(0.25),
            spreadRadius: 2,
            blurRadius: 7,
            offset: const Offset(0, 4), // changes position of shadow
          ),
        ],
      ),
      alignment: Alignment.center,
      child: Text("Field Guide", style: TextStyle(
        color: context.theme.backgroundColor,
        fontSize: 25.sp,
        fontWeight: FontWeight.w500,
      )),
    )
  );
}