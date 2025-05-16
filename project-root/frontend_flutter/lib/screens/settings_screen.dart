import 'package:flutter/material.dart';
import 'package:frontend_flutter/utilities/bottom_bar.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  void logOut() {}
  // TODO logout function

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Center(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: SizedBox(
                width: MediaQuery.of(context).size.width * 0.85,
                child: ElevatedButton(
                  onPressed: logOut,
                  child: const Text('Wyloguj się', style: TextStyle(fontSize: 18)),
                ),
              ),
            ),
          ],
        ),
      ),
      bottomNavigationBar: MyBottomBar(),
    );
  }
}
