import 'package:cached_network_image/cached_network_image.dart';
import 'package:eywa_client/view/common_widgets/detail_animal_page.dart';
import 'package:eywa_client/view/common_widgets/detail_plant_page.dart';
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
      Get.to(
        DetailPlant(
          element: element,
          imagePath: element.image,
        ),
        opaque: false,
        transition: Transition.downToUp
      );
    },
    child: Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20.r),
      ),
      child: Hero(
        tag: element.id.toString(),
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
              child: CachedNetworkImage(
                placeholder: (context, url) => Container(
                  child: Container(
                    width: 10.w,
                    height: 10.w,
                    color: Colors.transparent,
                    alignment: Alignment.center,
                    child: CircularProgressIndicator(
                      color: context.theme.primaryColorDark,
                    ),
                  ),
                ),
                errorWidget: (context, url, error) => Icon(
                  Icons.error,
                  color: context.theme.primaryColorDark,
                ),
                imageUrl: element.image,
                fit: BoxFit.cover,
              ),
            ),
          )
        ),
      ),
    ),
  );
}

Widget _gridViewElementAnimal(BuildContext context, FieldGuideElementAnimal element){
  return GestureDetector(
    onTap: (){
      Get.to(DetailAnimal(element: element, imagePath: element.image,), opaque: false, transition: Transition.downToUp);
    },
    child: Hero(
      tag: element.id.toString(),
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
            child: CachedNetworkImage(
              placeholder: (context, url) => Container(
                child: Container(
                  width: 10.w,
                  height: 10.w,
                  color: Colors.transparent,
                  alignment: Alignment.center,
                  child: CircularProgressIndicator(
                    color: context.theme.primaryColorDark,
                  ),
                ),
              ),
              errorWidget: (context, url, error) => Icon(
                Icons.error,
                color: context.theme.primaryColorDark,
              ),
              imageUrl: element.image,
              fit: BoxFit.cover,
            ),
          ),
        )
      ),
    ),
  );
}