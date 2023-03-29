import 'package:eywa_client/view_model/user_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class SignInPage extends StatelessWidget {
  const SignInPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: context.theme.backgroundColor,
      body: Container(
        width: double.infinity,
        height: double.infinity,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            _logo(),
            SizedBox(height: 261.h),
            _signInButton(context),
          ],
        ),
      ),
    );
  }

  Widget _logo() {
    return Hero(
      tag: "logo",
      child: Image.asset(
        "assets/images/logo.jpg",
        width: 250.w,
      ),
    );
  }

  Widget _signInButton(BuildContext context) {
    return ElevatedButton(
      onPressed: () {
        Get.find<UserController>().googleLogin().then((value){
          if (value) {
            Get.find<UserController>().signInSuccess();
            Get.offAllNamed("/home");
          }
        });
      },

      style: ElevatedButton.styleFrom(
        fixedSize: Size(271.w, 45.h),
        backgroundColor: context.theme.primaryColor,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(30.r),
        ),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Image.asset(
            "assets/images/google.png",
            width: 27.w,
            height: 27.h,
          ),
          SizedBox(width: 12.w),
          Text(
            "Sign in with Google",
            style: TextStyle(
              fontSize: 20.sp,
              fontWeight: FontWeight.w400,
              color: context.theme.backgroundColor,
            ),
          )
        ],
      ),
    );
  }
}