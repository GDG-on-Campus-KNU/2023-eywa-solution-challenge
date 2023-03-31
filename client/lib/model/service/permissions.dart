import 'package:geolocator/geolocator.dart';

Future<bool> geolocatorPermission() async {
  LocationPermission permission = await Geolocator.requestPermission();
  return true;
}