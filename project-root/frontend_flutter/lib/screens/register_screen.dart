import 'package:flutter/material.dart';
import 'package:frontend_flutter/api/http_service.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';
import 'package:frontend_flutter/utilities/navigation.dart';

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({super.key});

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final TextEditingController confirmPasswordController =
      TextEditingController();
  final TextEditingController usernameController = TextEditingController();

  void attemptRegister() async {
    String username = usernameController.text.trim();
    String email = emailController.text.trim();
    String password = passwordController.text.trim();
    String confirmPassword = confirmPasswordController.text.trim();

    bool registerSuccessful = await registerUser(username, email, password);

    if (email.isEmpty ||
        password.isEmpty ||
        confirmPassword.isEmpty ||
        username.isEmpty) {
      _showDialog("Błąd", "Pola nie mogą być puste");
      return;
    }

    if (password != confirmPassword) {
      _showDialog("Błąd", "Hasła nie są jednakowe.");
      return;
    }

    if (registerSuccessful) {
      await _showDialog(
        'Sukces',
        'Zarejestrowano, możesz się teraz zalogować :)',
      );
      goToLoginScreen(context);
    } else {
      _showDialog("Błąd", "Nazwa użytkownika jest już zajęta");
      return;
    }
  }

  Future<void> _showDialog(String title, String message) {
    return showDialog<void>(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text(title),
          content: Text(message),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text("OK"),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Text('Rejestracja', style: TextStyle(fontSize: 25)),
            Padding(
              padding: const EdgeInsets.all(10.0),
              child: SizedBox(
                width: MediaQuery.of(context).size.width * 0.80,
                child: TextField(
                  controller: usernameController,
                  decoration: const InputDecoration(
                    labelText: 'Nazwa użytkownika',
                    border: OutlineInputBorder(),
                  ),
                  autofocus: true,
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(10.0),
              child: SizedBox(
                width: MediaQuery.of(context).size.width * 0.80,
                child: TextField(
                  controller: emailController,
                  decoration: const InputDecoration(
                    labelText: 'E-mail',
                    border: OutlineInputBorder(),
                  ),
                  autofocus: true,
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(10.0),
              child: SizedBox(
                width: MediaQuery.of(context).size.width * 0.80,
                child: TextField(
                  controller: passwordController,
                  decoration: const InputDecoration(
                    labelText: 'Hasło',
                    border: OutlineInputBorder(),
                  ),
                  obscureText: true,
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(10.0),
              child: SizedBox(
                width: MediaQuery.of(context).size.width * 0.80,
                child: TextField(
                  controller: confirmPasswordController,
                  decoration: const InputDecoration(
                    labelText: 'Powtórz hasło',
                    border: OutlineInputBorder(),
                  ),
                  obscureText: true,
                ),
              ),
            ),
            SizedBox(
              width: MediaQuery.of(context).size.width * 0.25,
              child: ElevatedButton(
                onPressed: attemptRegister,
                child: const Text(
                  'Zarejestruj',
                  style: TextStyle(fontSize: 18),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
