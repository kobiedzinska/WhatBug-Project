import 'package:frontend_flutter/screens/camera_screen.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  void openCameraScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => const CameraScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: const Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'welcome to our app',
              style: TextStyle(fontSize: 25),
            ),
            Text(
              '☆*: .｡. o(≧▽≦)o .｡.:*☆',
              style: TextStyle(fontSize: 25),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: openCameraScreen,
        tooltip: 'Camera',
        child: const Icon(Icons.camera_alt),
      ), 
    );
  }
}