import 'package:eywa_client/model/service/alien_species_descriminate.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

class LoadingPage extends StatelessWidget {
  const LoadingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    loadModel();
    Get.find<UserController>().tryAutoSignIn().then((value) {
      if (value) {
        Get.find<UserController>().signInSuccess();
        Get.offAllNamed("/home");
      }
      else{
        Get.offAllNamed("/sign_in");
      }
    });
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
            _loadingIndicator(context),
          ],
        ),
      ),
    );
  }
}

_logo() {
  return Hero(
    tag: "logo",
    child: Image.asset(
      "assets/images/logo.jpg",
      width: 250.w,
    ),
  );
}

_loadingIndicator(BuildContext context) {
  return CircularProgressIndicator(
    color: context.theme.primaryColor,
  );
}