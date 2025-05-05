import 'package:flutter/material.dart';
import 'package:frontend_flutter/utilities/bottom_bar.dart';
import 'package:frontend_flutter/utilities/camera_button.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';
import 'package:frontend_flutter/utilities/navigation.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
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
      floatingActionButton: CameraButton(),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      bottomNavigationBar: MyBottomBar(),
    );
  }
}
