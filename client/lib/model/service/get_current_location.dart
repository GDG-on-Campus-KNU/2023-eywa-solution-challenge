import 'package:geolocator/geolocator.dart';

Future<Position> returnCurPosition(){
  return Geolocator.getCurrentPosition(desiredAccuracy: LocationAccuracy.high);
}