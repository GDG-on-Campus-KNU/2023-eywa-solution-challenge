import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

import 'app_bar.dart';
import 'navigation_bar.dart';
import 'view.dart';

class FieldGuidePage extends StatelessWidget {
  const FieldGuidePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Obx(() => Scaffold(
      body: Stack(
        alignment: Alignment.center,
        children: [
          fieldGuideView(context),
          fieldGuideAppBar(context),
          fieldGuideNavigationBar(context),
        ],
      )
    ));
  }
}
