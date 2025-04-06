import 'package:flutter/material.dart';
import 'package:frontend_flutter/screens/all_bugs_screen.dart';
import 'package:frontend_flutter/screens/camera_screen.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';

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

  void goToBugsScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => const AllBugsScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const MyAppBar(),
      body: Center(
        child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
          const Text(
            'welcome to our app',
            style: TextStyle(fontSize: 25),
          ),
          const Text(
            '☆*: .｡. o(≧▽≦)o .｡.:*☆',
            style: TextStyle(fontSize: 25),
          ),
          ElevatedButton(
            onPressed: goToBugsScreen,
            child: const Icon(Icons.bug_report),
          ),
        ]),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: openCameraScreen,
        tooltip: 'Camera',
        child: const Icon(Icons.camera_alt),
      ),
    );
  }
}