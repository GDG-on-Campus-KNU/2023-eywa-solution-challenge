import "package:flutter/material.dart";
import "package:flutter_screenutil/flutter_screenutil.dart";
import "package:get/get.dart";

import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'widget_result_card_fail.dart';
import 'navigation_bar.dart';
import 'widget_result_card_success.dart';

class SearchResultPage extends StatelessWidget {
  const SearchResultPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Obx(() => Scaffold(
      body: Stack(
        alignment: Alignment.center,
        children: [
          _background(context),
          Get.find<SearchPageViewController>().ifImageNull.value ?
          Container()
          : Get.find<SearchPageViewController>().ifImageAlienSpecies ?
              resultCardSuccess(context, Get.find<SearchPageViewController>().image!.path, Get.find<SearchPageViewController>().searchElement)
            : resultCardFail(context),
          searchPageNavigationBar(context),
        ],
      ),
    ));
  }
}

Widget _background(BuildContext context) => Positioned(
  child: Container(
    width: 390.w,
    height: 844.h,
    color: context.theme.backgroundColor,
  ),
);