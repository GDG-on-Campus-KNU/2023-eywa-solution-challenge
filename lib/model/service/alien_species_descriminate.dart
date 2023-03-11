
import 'dart:io';

import 'package:image_picker/image_picker.dart';
import 'package:tflite/tflite.dart';

void loadModel() {
  Tflite.loadModel(
    model: "assets/models/model.tflite",
    labels: "assets/models/labels.txt",
  );
}

void classifyImage(XFile image) async {
  var timeStamp = DateTime.now().millisecondsSinceEpoch;
  var output = await Tflite.runModelOnImage(
      path: File(image!.path).path,
      imageMean: 0.0, // defaults to 117.0
      imageStd: 255.0, // defaults to 1.0
      numResults: 2, // defaults to 5
      threshold: 0.2, // defaults to 0.1
      asynch: true // defaults to true
  );
  print("-------------> ${DateTime.now().millisecondsSinceEpoch - timeStamp}");
  print(output);
}