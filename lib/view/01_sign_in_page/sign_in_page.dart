import 'package:eywa_client/model/service/google_%20sign_in.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class SignInPage extends StatelessWidget {
  const SignInPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: context.theme.backgroundColor,
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          _logo(),
          SizedBox(height: 261.h),
          _signInButton(context),
        ],
      ),
    );
  }

  Widget _logo() {
    return Container();
  }

  Widget _signInButton(BuildContext context) {
    return ElevatedButton(
      onPressed: () {
        signInWithGoogle();
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