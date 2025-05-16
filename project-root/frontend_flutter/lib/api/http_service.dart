import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;

final _storage = FlutterSecureStorage();

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
  final String baseURL = "http://localhost:8080/api/bugs/all";

  Future<List<Bug>> getBugs() async {
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
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({'email': email, 'password': password}),
    );

    if (response.statusCode == 200) {
      
      final token = response.body;
      await _storage.write(key: 'jwt_token', value: token);

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
