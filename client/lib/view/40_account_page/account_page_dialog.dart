import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import 'account_page_body.dart';
import 'navigation_bar.dart';

BackdropFilter AccountPageDialog(BuildContext context){
  return BackdropFilter(
    filter: ImageFilter.blur(sigmaX: 3, sigmaY: 3),
    child: Dialog(
      insetPadding: EdgeInsets.all(0),
      backgroundColor: context.theme.backgroundColor.withOpacity(0.8),
      elevation: 0,
      child: Obx(() => Container(
        width: 390.w,
        height: 844.h,
        color: Colors.transparent,
        child: Stack(
          children: [
            accountPageBody(context),
            accountPageNavigationBar(context),
          ],
        ),
      )),
    ),
  );
}