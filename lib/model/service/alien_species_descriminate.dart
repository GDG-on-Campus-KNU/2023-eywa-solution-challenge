
import 'dart:io';

import 'package:image_picker/image_picker.dart';
import 'package:tflite/tflite.dart';

void loadModel() {
  Tflite.loadModel(
    model: "assets/models/model.tflite",
    labels: "assets/models/labels.txt",
  );
}

Future<int> classifyImage(XFile image) async {
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
  if(output!.isNotEmpty){
    String classifyOutput = output[0]["label"];
    String result = "";
    int ind = 0;

    try{
      for(ind = 0; ind < classifyOutput.length; ind++){
        if(classifyOutput[ind] == "'") {
          break;
        }
      }
      ind++;
      for(; ind < classifyOutput.length; ind++){
        if(classifyOutput[ind] == "'") {
          break;
        }
        result += classifyOutput[ind];
      }
      return int.parse(result);
    }
    catch(e){
      return 0;
    }
  }
  else{
    return 0;
  }
}