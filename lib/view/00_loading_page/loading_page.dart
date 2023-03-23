import 'package:eywa_client/model/service/alien_species_descriminate.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class LoadingPage extends StatelessWidget {
  const LoadingPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    loadModel();
    Get.find<UserController>().tryAutoSignIn().then((value) {
      if (value) {
        Get.find<UserController>().getPosition();
        Get.offAllNamed("/home");
      }
      else{
        Get.offAllNamed("/sign_in");
      }
    });
    return Container();
  }
}
