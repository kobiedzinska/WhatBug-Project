import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;

final _storage = FlutterSecureStorage();

class UserSession {
  static Future<String?> getUserId() => _storage.read(key: 'userId');
  static Future<String?> getUsername() => _storage.read(key: 'username');
  static Future<String?> getUserEmail() => _storage.read(key: 'userEmail');
  static Future<void> clearSession() => _storage.deleteAll();
}

class Bug {
  final int id;
  final String name;
  final String phylum;

  Bug({required this.id, required this.name, required this.phylum});

  factory Bug.fromJson(Map<String, dynamic> json) {
    return Bug(id: json['id'], name: json['name'], phylum: json['phylum']);
  }
}

class BugService {
  Future<List<Bug>> getBugs() async {
    final userId = await _storage.read(key: 'userId');
    final userEmail = await _storage.read(key: 'userEmail');

    if (kDebugMode) {
      print("userId from storage: $userId");
      print("userEmail from storage: $userEmail");
    }

    final String baseURL = "http://localhost:8080/api/bugs_found/all/$userId";

    final response = await http.get(Uri.parse(baseURL));

    if (response.statusCode == 200) {
      final data = jsonDecode(response.body);

      List<Bug> bugs = [];

      for (var bugJson in data) {
        bugs.add(Bug.fromJson(bugJson));
      }

      return bugs;
    } else {
      throw "Unable to retrieve bug data.";
    }
  }
}

Future<bool> loginUser(String email, String password) async {
  const String apiUrl = 'http://localhost:8080/api/auth/login';

  try {
    final response = await http.post(
      Uri.parse(apiUrl),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      body: jsonEncode({'email': email, 'password': password}),
    );

    if (response.statusCode == 200) {
      final responseBody = jsonDecode(response.body);
      final token = responseBody['accessToken'];
      final refreshToken = responseBody['refreshTokenUUID'];
      final userId = responseBody['id'];
      final username = responseBody['username'];
      final userEmail = responseBody['email'];

      await _storage.write(key: 'jwt_token', value: token);
      await _storage.write(key: 'accessToken', value: token);
      await _storage.write(key: 'refreshTokenUUID', value: refreshToken);
      await _storage.write(key: 'userId', value: userId.toString());
      await _storage.write(key: 'username', value: username.toString());
      await _storage.write(key: 'userEmail', value: userEmail.toString());

      return true;
    } else {
      if (kDebugMode) {
        print('Błąd logowania: ${response.statusCode}');
      }
    }
  } catch (e) {
    if (kDebugMode) {
      print('Exception while logging in: $e');
    }
  }
  return false;
}

Future<bool> registerUser(
  String username,
  String email,
  String password,
) async {
  const String apiUrl = 'http://localhost:8080/api/auth/register';

  try {
    final response = await http.post(
      Uri.parse(apiUrl),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'username': username,
        'email': email,
        'password': password,
      }),
    );

    if (response.statusCode == 201) {
      return true;
    } else {
      if (kDebugMode) {
        print('Błąd logowania: ${response.statusCode}');
      }
    }
  } catch (e) {
    if (kDebugMode) {
      print('Exception while logging in: $e');
    }
  }
  return false;
}
