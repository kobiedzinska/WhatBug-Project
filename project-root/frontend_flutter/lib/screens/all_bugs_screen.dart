import 'package:frontend_flutter/utilities/my_app_bar.dart';
import 'package:flutter/material.dart';
import 'package:frontend_flutter/api/http_service.dart';

class AllBugsScreen extends StatefulWidget {
  const AllBugsScreen({super.key});

  @override
  State<AllBugsScreen> createState() => _AllBugsScreenState();
}

class _AllBugsScreenState extends State<AllBugsScreen> {
  late Future<List<Bug>> futureBugs;

  @override
  void initState() {
    super.initState();
    futureBugs = BugService().getBugs();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: FutureBuilder(
          future: futureBugs,
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return const Center(
                child: CircularProgressIndicator(),
              );
            } else if (snapshot.hasError) {
              return Center(child: Text('Error: ${snapshot.error}'));
            } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
              return const Center(child: Text('No bugs found.'));
            } else {
              final bugs = snapshot.data!;
              return ListView.builder(
                  itemCount: bugs.length,
                  itemBuilder: (context, index) {
                    final bug = bugs[index];
                    return ListTile(
                      title: Text(bug.name),
                      subtitle: Text("Phylum: ${bug.phylum}"),
                    );
                  });
            }
          }),
    );
  }
}
