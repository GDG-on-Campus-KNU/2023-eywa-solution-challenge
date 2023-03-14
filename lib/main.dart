import 'package:eywa_client/view/10_home_page/home_page.dart';
import 'package:eywa_client/view/20_field_guide_page/field_guide_page.dart';
import 'package:eywa_client/view/30_search_result_page/search_result_page.dart';
import 'package:eywa_client/view_model/field_guide_page_controller.dart';
import 'package:eywa_client/view_model/home_page_controller.dart';
import 'package:eywa_client/view_model/search_page_view_controller.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:firebase_core/firebase_core.dart';

import 'view/00_loading_page/loading_page.dart';
import 'view/01_sign_in_page/sign_in_page.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  Firebase.initializeApp();
  return runApp(const Eywa());
}

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
            initialRoute: "/home",
            initialBinding: BindingsBuilder(() {
              Get.put(UserController());
              Get.put(HomePageController());
              Get.put(FieldGuidePageController());
              Get.put(SearchPageViewController());
            }),

            getPages: [
              //Loading Page
              GetPage(
                name: "/",
                page: () => const LoadingPage(),
              ),

              //Sign In Page
              GetPage(
                name: "/sign_in",
                page: () => const SignInPage(),
              ),

              //Home Page
              GetPage(
                name: "/home",
                page: () => const HomePage(),
              ),

              //Field Guide Page
              GetPage(
                name: "/field_guide",
                page: () => const FieldGuidePage(),
                transition: Transition.downToUp,
                transitionDuration: Duration(milliseconds: 100),
              ),

              //Search Page
              GetPage(
                name: "/search_result",
                page: () => const SearchResultPage(),
                transition: Transition.downToUp,
                transitionDuration: Duration(milliseconds: 100),
              ),

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