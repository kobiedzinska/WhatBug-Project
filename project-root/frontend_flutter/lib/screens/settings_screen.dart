import 'package:flutter/material.dart';
import 'package:frontend_flutter/api/http_service.dart';
import 'package:frontend_flutter/utilities/bottom_bar.dart';
import 'package:frontend_flutter/utilities/camera_button.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  String? _username;

  @override
  void initState() {
    super.initState();
    _loadUserInfo();
  }

  void logOut() {}
  // TODO logout function

  void _loadUserInfo() async {
    final username = await UserSession.getUsername();

    setState(() {
      _username = username;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Center(
        child: Column(
          children: [
            SizedBox(height: 40),

            Icon(Icons.person_rounded, size: 50),

            Text("Hej, $_username!", style: TextStyle(fontSize: 30)),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: SizedBox(
                width: MediaQuery.of(context).size.width * 0.85,
                child: ElevatedButton(
                  onPressed: logOut,
                  child: const Text(
                    'Wyloguj się',
                    style: TextStyle(fontSize: 18),
                  ),
                ),
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
