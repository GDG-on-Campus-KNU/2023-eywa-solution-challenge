import 'package:eywa_client/view/10_home_page/home_map.dart';
import 'package:eywa_client/view/10_home_page/home_navigation_bar.dart';
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        alignment: Alignment.center,
        children: [
          homeMap(),
          homeNavigationBar(context),
        ],
      )
    );
  }

}
