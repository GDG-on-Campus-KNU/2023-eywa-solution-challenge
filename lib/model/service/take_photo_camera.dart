import 'package:image_picker/image_picker.dart';

Future<XFile?> takePhotoCamera() async {
  return await ImagePicker().pickImage(source: ImageSource.camera);
}