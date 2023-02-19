import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

void main() => runApp(const Eywa());

class Eywa extends StatelessWidget {
  const Eywa({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
      designSize: const Size(390, 844),
      builder: (context, child) {
        return GetMaterialApp(
          title: 'Eywa',
          debugShowCheckedModeBanner: false,
            initialRoute: "/",
            initialBinding: BindingsBuilder(() {}),
            getPages: const [
              // GetPage(
              //   name: "/<Name>",
              //   page: () => Widget(),
              //   binding: BindingsBuilder(() {
              //   }),
              // ),
            ],
          theme: ThemeData(
            fontFamily: "noto_sans",

            primaryColorDark: Color(0xFF674188),
            primaryColor: Color(0xFFC3ACD0),
            primaryColorLight: Color(0xFFF7EFE5),
            backgroundColor: Color(0xFFFFFBF5),
          ),
        );
      }
    );
  }
}