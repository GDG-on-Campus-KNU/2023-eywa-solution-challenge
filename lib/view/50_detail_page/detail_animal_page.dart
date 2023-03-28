import 'dart:ui';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:cross_file_image/cross_file_image.dart';
import 'package:eywa_client/model/field_guid_element.dart';
import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

class DetailAnimal extends StatelessWidget {
  FieldGuideElementAnimal element;
  String? imagePath;
  XFileImage? xFileImage;
  DetailAnimal({
    Key? key,
    required this.element,
    this.imagePath,
    this.xFileImage,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BackdropFilter(
      filter: ImageFilter.blur(sigmaX: 3, sigmaY: 3),
      child: Scaffold(
        backgroundColor: Colors.transparent,

        body: Obx(() => Stack(
          alignment: Alignment.center,
          children: [
            _background(),
            _detail(context, element),
            _image(imagePath, xFileImage, element.id),
          ],
        )),
      ),
    );
  }
}

Widget _background(){
  return Positioned(
    width: 390.w,
    height: 844.h,
    child: Container(
      color: Colors.transparent,
      child: GestureDetector(
        onTap: (){
          Get.find<FieldGuidePageController>().initCardIndex();
          Get.back();
        },
      ),
    )
  );
}

Widget _image(String? imagePath, XFileImage? xFileImage, int id){
  return Positioned(
    bottom: 485.h,
    child: Hero(
      tag: id.toString(),
      child: Container(
        width: 250.w,
        height: 250.w,
        child: ClipRRect(
          borderRadius: BorderRadius.circular(15.r),
          child: imagePath == null ?
          Image(
            image: xFileImage!,
            fit: BoxFit.cover,
          ) :
          CachedNetworkImage(
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
            imageUrl: imagePath,
            fit: BoxFit.cover,
          ),
        ),
      ),
    ),
  );
}

Widget _detail(BuildContext context, FieldGuideElementAnimal element){
  return Positioned(
    bottom: 0,
    child: Container(
      width: 390.w,
      height: 610.h,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(20.r),
          topRight: Radius.circular(20.r),
        ),
        color: context.theme.primaryColor,
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(height: 134.h),
          Text(element.engName, style: TextStyle(
            fontSize: 25.sp,
            fontWeight: FontWeight.w700,
            color: context.theme.backgroundColor,
          )),
          SizedBox(height: 9.h),
          _cardSection(context, element),
        ],
      ),
    ),
  );
}

Widget _cardSection(BuildContext context, FieldGuideElementAnimal element){
  return Container(
    width: 390.w,
    height: 340.h,
    child: Stack(
      children: [
        AnimatedPositioned(
          duration: Duration(milliseconds: 200),
          width: (250.w * 5) + (10.w * 4),
          left: -1 * (
            (250.w * (Get.find<FieldGuidePageController>().cardIndex.value - 1) )
          + (10.w * (Get.find<FieldGuidePageController>().cardIndex.value - 1) )
          + 190.w
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              AnimatedScale(
                scale: Get.find<FieldGuidePageController>().cardIndex.value == 0 ? 1 : 0.8,
                duration: Duration(milliseconds: 200),
                child: _cardElement(context, "ecological", element.ecological),
              ),
              SizedBox(width: 10.w,),
              AnimatedScale(
                scale: Get.find<FieldGuidePageController>().cardIndex.value == 1 ? 1 : 0.8,
                duration: Duration(milliseconds: 200),
                child: _cardElement(context, "introduction", element.introduction),
              ),
              SizedBox(width: 10.w,),
              AnimatedScale(
                scale: Get.find<FieldGuidePageController>().cardIndex.value == 2 ? 1 : 0.8,
                duration: Duration(milliseconds: 200),
                child: _cardElement(context, "effect", element.effect),
              ),
              SizedBox(width: 10.w,),
              AnimatedScale(
                scale: Get.find<FieldGuidePageController>().cardIndex.value == 3 ? 1 : 0.8,
                duration: Duration(milliseconds: 200),
                child: _cardElement(context, "regulate", element.regulate),
              ),
              SizedBox(width: 10.w,),
              AnimatedScale(
                scale: Get.find<FieldGuidePageController>().cardIndex.value == 4 ? 1 : 0.8,
                duration: Duration(milliseconds: 200),
                child: _cardElement(context, "designation", element.designation),
              ),
            ],
          )
        ),

        Positioned(
          left: 0,
          child: GestureDetector(
            onTap: (){
              Get.find<FieldGuidePageController>().decreaseCardIndex();
            },
            onVerticalDragEnd: (value){
              Get.find<FieldGuidePageController>().decreaseCardIndex();
            },
            child: Container(
              width: 70.w,
              height: 340.h,
              color: Colors.transparent,
            ),
          )
        ),
        Positioned(
          right: 0,
          child: GestureDetector(
            onTap: (){
              Get.find<FieldGuidePageController>().increaseCardIndex();
            },
            onVerticalDragEnd: (value){
              Get.find<FieldGuidePageController>().increaseCardIndex();
            },
            child: Container(
              width: 70.w,
              height: 340.h,
              color: Colors.transparent,
            ),
          )
        ),
      ],
    ),
  );
}

Widget _cardElement(BuildContext context, String title, Map<String, String> content){
  return Container(
    width: 250.w,
    height: 340.h,
    alignment: Alignment.center,
    decoration: BoxDecoration(
      color: context.theme.backgroundColor,
      borderRadius: BorderRadius.circular(20.r),
    ),
    child: Column(
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        Container(
          height: 30.h,
          width: 100.w,
          margin: EdgeInsets.symmetric(vertical: 15.h),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.all(Radius.circular(20.r)),
            color: context.theme.primaryColorDark,
          ),
          alignment: Alignment.center,
          child: Text(title, style: TextStyle(
            fontSize: 15.sp,
            fontWeight: FontWeight.w500,
            color: context.theme.primaryColorLight,
          )),
        ),

        Container(
          width: 202.w,
          height: 270.h,
          child: SingleChildScrollView(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: content.entries.map((e) {
                return Container(
                  width: 202.w,
                  margin: EdgeInsets.only(bottom: 30.h),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(e.key, style: TextStyle(
                        fontSize: 15.sp,
                        fontWeight: FontWeight.w600,
                        color: context.theme.primaryColorDark,
                      )),
                      Text(e.value, style: TextStyle(
                        fontSize: 10.sp,
                        fontWeight: FontWeight.w500,
                        color: context.theme.primaryColorDark,
                      )),
                    ],
                  ),
                );
              }).toList(),
            ),
          ),
        ),
      ],
    ),
  );
}