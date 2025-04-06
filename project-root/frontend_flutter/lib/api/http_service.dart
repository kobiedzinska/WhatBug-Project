import 'dart:convert';
import 'package:http/http.dart' as http;

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
