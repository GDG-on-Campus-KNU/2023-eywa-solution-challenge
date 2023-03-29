import 'package:eywa_client/view_model/home_page_controller.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

Widget accountPageBody(BuildContext context){
  return Positioned(
    child: Container(
      width: 390.w,
      padding: EdgeInsets.symmetric(horizontal: 36.w, vertical: 32.h),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          _accountAndLevel(context),
          SizedBox(height: 62.h,),
          _information(context),
          SizedBox(height: 62.h,),
          _signOut(context),
        ],
      ),
    ),
  );
}

////////////////////////////////////////////////////////////////////////////////Account and Level
Widget _accountAndLevel(BuildContext context) => Container(
  width: 318.w,
  height: 134.h,
  child: Column(
    mainAxisAlignment: MainAxisAlignment.spaceBetween,
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      _account(context),
      _level(context),
    ],
  )
);

Widget _account(BuildContext context) => Container(
  height: 74.h,
  child: Row(
    mainAxisAlignment: MainAxisAlignment.start,
    children:[
      Container(
        width: 59.h,
        height: 59.h,
        decoration: BoxDecoration(
          shape: BoxShape.circle,
          image: DecorationImage(
            image: NetworkImage(Get.find<UserController>().member.value.profileImage),
            fit: BoxFit.cover,
          ),
        ),
      ),

      SizedBox(width: 8.w,),

      Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(Get.find<UserController>().member.value.name,
            style: TextStyle(
              fontSize: 40.sp,
              fontWeight: FontWeight.w600,
              height: 1,
              color: context.theme.primaryColorDark,
            ),
          ),
          Text(Get.find<UserController>().member.value.email,
            style: TextStyle(
              fontSize: 15.sp,
              fontWeight: FontWeight.w600,
              height: 1,
              color: context.theme.primaryColorDark,
            ),
          ),
        ],
      )
    ]
  ),
);
Widget _level(BuildContext context) => Container(
  width: 316.w,
  height: 37.h,
  child: Column(
    children: [
      Column(
        children: [
          Flex(direction: Axis.horizontal, children: [
            Expanded(
              flex: Get.find<UserController>().member.value.exp,
              child: Container(
                height: 10.h,
                decoration: BoxDecoration(
                  color: context.theme.primaryColorDark,
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(5.r),
                    bottomLeft: Radius.circular(5.r),
                  ),
                ),
              ),
            ),
            Container(
              width: 4.w,
              height: 10.h,
              decoration: BoxDecoration(
                color: context.theme.primaryColorLight,
                borderRadius: BorderRadius.all(Radius.circular(5.r)),
              )
            ),
            Expanded(
              flex: 100 - Get.find<UserController>().member.value.exp,
              child: Container(
                height: 10.h,
                decoration: BoxDecoration(
                  color: context.theme.primaryColor,
                  borderRadius: BorderRadius.only(
                    topRight: Radius.circular(5.r),
                    bottomRight: Radius.circular(5.r),
                  ),
                )
              ),
            ),
          ]),
        ],
      ),
      Column(
        children: [
          Flex(direction: Axis.horizontal, children: [
            Expanded(
              flex: Get.find<UserController>().member.value.exp,
              child: Container(),
            ),
            Text("Lv.${Get.find<UserController>().member.value.level}", style: TextStyle(
              fontSize: 12.sp,
              fontWeight: FontWeight.w600,
              color: context.theme.primaryColorDark,
            ),),
            Expanded(
              flex: 100 - Get.find<UserController>().member.value.exp,
              child: Container(),
            ),
          ]),
        ],
      ),
    ],
  )
);

////////////////////////////////////////////////////////////////////////////////Information
Widget _information(BuildContext context) => Container(
  width: 194.w,
  height: 73.h,
  child: Column(
    mainAxisAlignment: MainAxisAlignment.spaceBetween,
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      Text("Animals I found : ${Get.find<UserController>().animalsFound}", style: TextStyle(
        fontSize: 20.sp,
        fontWeight: FontWeight.w600,
        color: context.theme.primaryColorDark,
      ),),
      Text("Plants I found : ${Get.find<UserController>().plantsFound}", style: TextStyle(
        fontSize: 20.sp,
        fontWeight: FontWeight.w600,
        color: context.theme.primaryColorDark,
      ),)
    ],
  )
);


////////////////////////////////////////////////////////////////////////////////Sign Out
Widget _signOut(BuildContext context) => GestureDetector(
  onTap: (){
    Get.toNamed("/sign_in");
  },
  child: Container(
    width: 117.w,
    height: 27.h,
    child: Row(
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        Text("Sign Out", style: TextStyle(
          fontSize: 20.sp,
          fontWeight: FontWeight.w600,
          color: context.theme.primaryColorDark,
        ),),
        SizedBox(width: 5.w,),
        Image.asset("assets/icons/sign_out.png", width: 27.h, height: 27.h,),
      ],
    )
  ),
);