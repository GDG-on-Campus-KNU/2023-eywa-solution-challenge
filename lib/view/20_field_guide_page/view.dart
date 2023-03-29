import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import 'view_grid.dart';
import 'view_list.dart';

Widget fieldGuideView(BuildContext context) {
  return Positioned(
    width: 390.w,
    height: 784.h,
    top: 60.h,
    child: Stack(
      children: [
        Positioned(
          width: 390.w,
          height: 730.h,
          child: AnimatedSwitcher(
            duration: Duration(milliseconds: 500),
            switchInCurve: Curves.fastOutSlowIn,
            child: (Get.find<FieldGuidePageController>().ifGrid.value) ?
              fieldGuideGridView(context) :
              fieldGuideListView(context),
          ),
        ),
        _viewButton(context),
      ],
    )
  );
}

Widget _viewButton(BuildContext context) => Positioned(
  bottom: 69.h,
  right: 14.w,
  child: Container(
    width: 61.w * 2,
    height: 43.h,
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(20.r),
      boxShadow: [
        BoxShadow(
          color: Colors.black.withOpacity(0.25),
          spreadRadius: 2,
          blurRadius: 7,
          offset: const Offset(0, 4), // changes position of shadow
        ),
      ],
    ),
    child: Row(
      children: [
        GestureDetector(
          onTap: (){
            Get.find<FieldGuidePageController>().viewChange(true);
          },
          child: Container(
            width: 61.w,
            height: 43.h,
            decoration: BoxDecoration(
              color: (Get.find<FieldGuidePageController>().ifGrid.value) ?
                context.theme.primaryColor :
                context.theme.backgroundColor,
              borderRadius: BorderRadius.only(
                topLeft: Radius.circular(20.r),
                bottomLeft: Radius.circular(20.r),
              ),
            ),
            child: Icon(
              Icons.grid_view_rounded,
              color: (Get.find<FieldGuidePageController>().ifGrid.value) ?
              context.theme.backgroundColor :
              context.theme.primaryColor,
              size: 25.w,
            ),
          ),
        ),
        GestureDetector(
          onTap: (){
            Get.find<FieldGuidePageController>().viewChange(false);
          },
          child: Container(
            width: 61.w,
            height: 43.h,
            decoration: BoxDecoration(
              color: (Get.find<FieldGuidePageController>().ifGrid.value) ?
                context.theme.backgroundColor :
                context.theme.primaryColor,
              borderRadius: BorderRadius.only(
                topRight: Radius.circular(20.r),
                bottomRight: Radius.circular(20.r),
              ),
            ),
            child: Icon(
              Icons.view_list_rounded,
              color: (Get.find<FieldGuidePageController>().ifGrid.value) ?
              context.theme.primaryColor :
              context.theme.backgroundColor,
              size: 25.w,
            ),
          ),
        ),
      ],
    ),
  )
);