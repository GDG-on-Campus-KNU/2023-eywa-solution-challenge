import 'package:eywa_client/model/field_guid_element.dart';
import 'package:eywa_client/model/register.dart';
import 'package:eywa_client/model/report.dart';
import 'package:eywa_client/model/service/alien_species_descriminate.dart';
import 'package:eywa_client/model/service/take_photo_camera.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';

class SearchPageViewController extends GetxController {

  @override
  void onInit() {
    super.onInit();
    loadModel();
  }

  RxBool ifImageNull = true.obs;
  XFile? image;

  bool ifImageAlienSpecies = true;

  FieldGuideElement searchElement = FieldGuideElement(
    id: 5,
    korName: "test",
    engName: "test",
    summary: "test",
    kind: "test",
    image: "test",
    registered: false,
  );

  Future<bool> takePhoto() async {
    image = await takePhotoCamera();

    if(image == null) {
      ifImageNull(true);
      return false;
    }
    classifyImage(image!);
    ifImageNull(false);
    return true;
  }

  //////////////////////////////////////////////////////////////////////////////Register
  Future<bool> registerToServer() async{
    if( await Register.POSTRegister(
      register: Register(
        coor: Get.find<UserController>().curPosition.value,
        dictionaryId: searchElement.id,
      )
    )){
      return true;
    }
    else{
      return false;
    }
  }

  //////////////////////////////////////////////////////////////////////////////Report
  Future<bool> reportToServer() async{
    if( await Report.POSTReport(
      report: Report(
        registerInfo: Register(
          coor: Get.find<UserController>().curPosition.value,
          dictionaryId: searchElement.id,
        ),
        ImagePath: image!.path,
      ),
    )){
      return true;
    }
    else{
      return false;
    }
  }

}