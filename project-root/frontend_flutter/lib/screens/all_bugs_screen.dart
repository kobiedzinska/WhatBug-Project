import 'package:flutter/material.dart';
import 'package:frontend_flutter/api/http_service.dart';
import 'package:frontend_flutter/utilities/bottom_bar.dart';
import 'package:frontend_flutter/utilities/bug_card.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';

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
      appBar: const MyAppBar(),
      body: FutureBuilder(
        future: futureBugs,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Błąd: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text('Nie masz jeszcze znalezionych robaków', style: TextStyle(fontSize: 18),));
          } else {
            final bugs = snapshot.data!;
            return ListView.builder(
              itemCount: bugs.length,
              itemBuilder: (context, index) {
                final bug = bugs[index];
                return BugCard(
                  bugName: bug.name,
                  bugInfo: "Phylum: ${bug.phylum}",
                  bugPicture: AssetImage('lib/images/appIcon.png'),
                );
              },
            );
          }
        },
      ),
      bottomNavigationBar: MyBottomBar(),
    );
  }
}
