import 'package:eywa_client/model/field_guid_element.dart';
import 'package:eywa_client/model/register.dart';
import 'package:eywa_client/model/report.dart';
import 'package:eywa_client/model/service/alien_species_descriminate.dart';
import 'package:eywa_client/model/service/take_photo_camera.dart';
import 'package:eywa_client/view_model/user_controller.dart';
import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';

class SearchPageViewController extends GetxController {

  Rx<XFile>? image;

  RxInt classifiedSpeciesDictionary = 0.obs;

  FieldGuideElementPlant? plantSearchElement;
  FieldGuideElementAnimal? animalSearchElement;

  Future<bool> takePhoto(bool ifCamera) async {
    XFile? newImage = ifCamera ? await takePhotoCamera() : await takePhotoGallery();
    if(newImage == null) {
      return false;
    }

    image = newImage.obs;
    int newClassifiedSpeciesDictionary = await classifyImage(newImage);
    classifiedSpeciesDictionary(newClassifiedSpeciesDictionary);
    if(newClassifiedSpeciesDictionary == 0) {
      return true;
    }

    plantSearchElement = FieldGuideElementPlant.fieldGuideElementPlantFromId(newClassifiedSpeciesDictionary);
    animalSearchElement = FieldGuideElementAnimal.fieldGuideElementAnimalFromId(newClassifiedSpeciesDictionary);

    return true;
  }

  //////////////////////////////////////////////////////////////////////////////Register
  Future<bool> registerToServer() async{
    if( await Register.POSTRegister(
      register: Register(
        coor: Get.find<UserController>().curPosition.value,
        dictionaryId: classifiedSpeciesDictionary.value,
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
          dictionaryId: classifiedSpeciesDictionary.value,
        ),
        imagePath: image!.value.path,
        reportId: 0,
      ),
    )){
      return true;
    }
    else{
      return false;
    }
  }

}