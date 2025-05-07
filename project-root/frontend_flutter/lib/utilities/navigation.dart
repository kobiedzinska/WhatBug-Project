import 'package:flutter/material.dart';
import 'package:frontend_flutter/screens/all_bugs_screen.dart';
import 'package:frontend_flutter/screens/camera_screen.dart';
import 'package:frontend_flutter/screens/home_screen.dart';
import 'package:frontend_flutter/screens/login_screen.dart';
import 'package:frontend_flutter/screens/register_screen.dart';
import 'package:frontend_flutter/screens/settings_screen.dart';

void goToLoginScreen(BuildContext context) {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const LoginScreen()),
  );
}

void goToRegisterScreen(BuildContext context) {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const RegisterScreen()),
  );
}

void goToHomePage(BuildContext context) {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const HomePage()),
  );
}

void openCameraScreen(BuildContext context) {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const CameraScreen()),
  );
}

void goToBugsScreen(BuildContext context) {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const AllBugsScreen()),
  );
}

void goToSettingsScreen(BuildContext context) {
  Navigator.push(
    context,
    MaterialPageRoute(builder: (context) => const SettingsScreen()),
  );
}
