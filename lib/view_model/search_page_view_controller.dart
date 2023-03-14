import 'package:eywa_client/model/field_guid_element.dart';
import 'package:eywa_client/model/service/alien_species_descriminate.dart';
import 'package:eywa_client/model/service/take_photo_camera.dart';
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
    id: 0,
    korName: "test",
    engName: "test",
    summary: "test",
    kind: "test",
    image: "test"
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
}