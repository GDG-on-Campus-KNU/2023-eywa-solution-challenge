import 'package:eywa_client/view/21_detail_dialog/detail_dialog_animal.dart';
import 'package:eywa_client/view/21_detail_dialog/detail_dialog_plant.dart';
import "package:flutter/material.dart";
import "package:flutter_screenutil/flutter_screenutil.dart";
import "package:get/get.dart";

import 'package:eywa_client/model/field_guid_element.dart';
import 'package:eywa_client/view_model/field_guide_page_controller.dart';

Widget fieldGuideGridView(BuildContext context){
  return Container(
    alignment: Alignment.center,
    color: Colors.transparent,
    child: SingleChildScrollView(
      child: Wrap(
        children: Get.find<FieldGuidePageController>().ifPlant.value ?
        Get.find<FieldGuidePageController>().plantElements.map(
          (e) => _gridViewElementPlant(context, e)
        ).toList()
        : Get.find<FieldGuidePageController>().animalElements.map(
          (e) => _gridViewElementAnimal(context, e)
        ).toList(),
      ),
    ),
  );
}

Widget _gridViewElementPlant(BuildContext context, FieldGuideElementPlant element){
  return GestureDetector(
    onTap: (){
      Get.dialog(
        DetailDialogPlant(context, element),
        barrierColor: Color(0x00000000),
      );
    },
    child: Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20.r),
      ),
      child: Container(
        width: 100.w,
        height: 100.w,
        margin: EdgeInsets.all(11.w),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20.r),
          border: Border.all(
            color: context.theme.primaryColorDark,
            width: 5.w),
        ),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(15.r),
          child: ColorFiltered(
            colorFilter: element.registered ?
            ColorFilter.mode(Colors.transparent, BlendMode.saturation)
            : ColorFilter.mode(Colors.grey, BlendMode.saturation),
            child: Image.network(
              element.image,
              fit: BoxFit.cover,
            ),
          ),
        )
      ),
    ),
  );
}

Widget _gridViewElementAnimal(BuildContext context, FieldGuideElementAnimal element){
  return GestureDetector(
    onTap: (){
      Get.dialog(DetailDialogAnimal(context, element));
    },
    child: Hero(
      tag: "fieldGuideElement",
      child: Container(
        width: 100.w,
        height: 100.w,
        margin: EdgeInsets.all(11.w),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20.r),
          border: Border.all(
              color: context.theme.primaryColorDark,
              width: 5.w),
        ),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(15.r),
          child: ColorFiltered(
            colorFilter: element.registered ?
            ColorFilter.mode(Colors.transparent, BlendMode.saturation)
                : ColorFilter.mode(Colors.grey, BlendMode.saturation),
            child: Image.network(
              element.image,
              fit: BoxFit.cover,
            ),
          ),
        )
      ),
    ),
  );
}