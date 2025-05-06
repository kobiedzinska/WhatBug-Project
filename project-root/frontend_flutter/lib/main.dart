import 'package:flutter/material.dart';
import 'package:frontend_flutter/screens/welcome_screen.dart';
import 'package:google_fonts/google_fonts.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Insektorium Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: const Color(0xFF5F6C38)),
        useMaterial3: true,
        textTheme: GoogleFonts.atmaTextTheme(),
      ),
      home: const WelcomePage(title: 'Insektorium'),
      debugShowCheckedModeBanner: false,
    );
  }
}
