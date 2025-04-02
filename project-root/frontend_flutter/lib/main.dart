import 'package:flutter/material.dart';
import 'package:frontend_flutter/screens/home_screen.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of our application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Insektorium Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: const Color(0xFF5F6C38)),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Insektorium'),
      debugShowCheckedModeBanner: false,
    );
  }
}


