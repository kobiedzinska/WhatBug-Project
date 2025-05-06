import 'package:flutter/material.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';
import 'package:frontend_flutter/utilities/navigation.dart';

class WelcomePage extends StatefulWidget {
  const WelcomePage({super.key, required this.title});
  final String title;

  @override
  State<WelcomePage> createState() => _WelcomePageState();
}

class _WelcomePageState extends State<WelcomePage> {
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
                        style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
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

            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: SizedBox(
                    width: MediaQuery.of(context).size.height * 0.20,
                    height: MediaQuery.of(context).size.height * 0.08,
                    child: ElevatedButton(
                      onPressed: () => goToLoginScreen(context),
                      child: const Text(
                        'Zaloguj',
                        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                      ),
                    ),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: SizedBox(
                    width: MediaQuery.of(context).size.height * 0.20,
                    height: MediaQuery.of(context).size.height * 0.08,
                    child: ElevatedButton(
                      onPressed: () => goToRegisterScreen(context),
                      child: const Text(
                        'Zarejestruj',
                        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                      ),
                    ),
                  ),
                ),
              ],
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: SizedBox(
                height: MediaQuery.of(context).size.height * 0.06,
                child: ElevatedButton(
                  onPressed: () => goToHomePage(context),
                  child: const Text(
                    'Kontynuuj jako gość',
                    style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                ),
              ),
            ),
            SizedBox(height: MediaQuery.of(context).size.height * 0.05),
          ],
        ),
      ),
    );
  }
}
