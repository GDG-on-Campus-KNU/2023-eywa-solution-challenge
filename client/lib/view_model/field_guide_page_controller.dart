import 'package:get/get.dart';

import 'package:eywa_client/model/field_guid_element.dart';

class FieldGuidePageController extends GetxController {
  @override
  void onInit() {
    super.onInit();

    getFieldGuideElementsList();
  }

  //////////////////////////////////////////////////////////////////////////////view button
  RxBool ifGrid = true.obs;
  void viewChange(bool ifChangeToGrid){
    if(ifChangeToGrid == true && ifGrid.value == false){
      ifGrid(true);
    }
    else if(ifChangeToGrid == false && ifGrid.value == true){
      ifGrid(false);
    }
  }

  //////////////////////////////////////////////////////////////////////////////NavigationBar
  RxBool ifPlant = true.obs;
  void navigationChange(bool ifChangeToPlant){
    if(ifChangeToPlant == true && ifPlant.value == false){
      ifPlant(true);
    }
    else if(ifChangeToPlant == false && ifPlant.value == true){
      ifPlant(false);
    }
  }
  
  //////////////////////////////////////////////////////////////////////////////Field Guide Element
  RxList<FieldGuideElementPlant> plantElements = <FieldGuideElementPlant>[].obs;
  RxList<FieldGuideElementAnimal> animalElements = <FieldGuideElementAnimal>[].obs;

  void getFieldGuideElementsList() async{
    FieldGuideElementPlantAndAnimal newList = await FieldGuideElementPlantAndAnimal.GETFieldGuideList();
    plantElements(newList.plants);
    animalElements(newList.animals);
  }

  //////////////////////////////////////////////////////////////////////////////Detail Page
  Rx<int> cardIndex = 0.obs;
  void initCardIndex() {
    cardIndex(0);
  }
  void decreaseCardIndex() {
    if (cardIndex.value >= 1) cardIndex(cardIndex.value - 1);
  }
  void increaseCardIndex() {
    if (cardIndex.value <= (ifPlant.value ? 1 : 4)) cardIndex(cardIndex.value + 1);
  }
}