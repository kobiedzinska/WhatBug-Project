import 'package:flutter/material.dart';
import 'package:frontend_flutter/screens/all_bugs_screen.dart';
import 'package:frontend_flutter/screens/camera_screen.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  void openCameraScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const CameraScreen()),
    );
  }

  void goToBugsScreen() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const AllBugsScreen()),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const MyAppBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Expanded(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  Column(
                    children: [
                      const Text(
                        'welcome to our app',
                        style: TextStyle(fontSize: 25),
                      ),
                      const Text(
                        '☆*: .｡. o(≧▽≦)o .｡.:*☆',
                        style: TextStyle(fontSize: 25),
                      ),
                    ],
                  ),
                  Image(image: AssetImage('lib/images/appIcon.png')),
                ],
              ),
            ),
          ],
        ),
      ),

      bottomNavigationBar: BottomAppBar(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            IconButton(onPressed: () {}, icon: const Icon(Icons.home)),
            IconButton(onPressed: () {}, icon: const Icon(Icons.bug_report)),
            IconButton(onPressed: () {}, icon: const Icon(Icons.settings)),
          ],
        ),
      ),
    );
  }
}
