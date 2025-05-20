import 'package:flutter/material.dart';
import 'package:frontend_flutter/api/http_service.dart';
import 'package:frontend_flutter/utilities/bottom_bar.dart';
import 'package:frontend_flutter/utilities/bug_card.dart';
import 'package:frontend_flutter/utilities/camera_button.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';
import 'package:google_fonts/google_fonts.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  Future<List<Bug>>? futureBugs;

  @override
  void initState() {
    super.initState();
    futureBugs = BugService().getBugs();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const MyAppBar(),
      body: SingleChildScrollView(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              SizedBox(height: 20),
              Divider(
                thickness: 4,
                indent: MediaQuery.of(context).size.width * 0.05,
                endIndent: MediaQuery.of(context).size.width * 0.05,
              ),
              SizedBox(
                width: MediaQuery.of(context).size.width * 0.90,
                child: Row(
                  children: [
                    Icon(Icons.star_border_rounded, size: 35),
                    Text(
                      'Did you know... ?',
                      style: GoogleFonts.atma(
                        fontSize: 28,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Card(
                  child: SizedBox(
                    width: MediaQuery.of(context).size.width * 0.90,
                    child: Padding(
                      padding: const EdgeInsets.all(10.0),
                      child: Column(
                        children: [
                          Text(
                            'Odorek zielony, jeśli zostanie wystraszony, wydziela mocny, nieprzyjemny zapach by odstraszyć potencjalne niebezpieczeństwo - stąd jego nazwa.',
                            style: GoogleFonts.atma(
                              fontSize: 20,
                              fontWeight: FontWeight.normal,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              ),
              Divider(
                thickness: 4,
                indent: MediaQuery.of(context).size.width * 0.05,
                endIndent: MediaQuery.of(context).size.width * 0.05,
              ),
              SizedBox(
                width: MediaQuery.of(context).size.width * 0.90,
                child: Text(
                  'Recently found',
                  style: GoogleFonts.atma(
                    fontSize: 28,
                    fontWeight: FontWeight.bold,
                  ),
                  textAlign: TextAlign.left,
                ),
              ),
              FutureBuilder(
                future: futureBugs,
                builder: (context, snapshot) {
                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return const Center(child: CircularProgressIndicator());
                  } else if (snapshot.hasError) {
                    return Center(child: Text('Błąd: ${snapshot.error}'));
                  } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
                    return const Center(
                      child: Text(
                        'Nie masz jeszcze znalezionych robaków',
                        style: TextStyle(fontSize: 18),
                      ),
                    );
                  } else {
                    final allBugs = snapshot.data!;
                    final bugs =
                        allBugs.length > 3 ? allBugs.sublist(0, 3) : allBugs;
                    return ListView.builder(
                      shrinkWrap: true,
                      physics: NeverScrollableScrollPhysics(),
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
              SizedBox(height: 110),
            ],
          ),
        ),
      ),
      floatingActionButton: CameraButton(),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
      bottomNavigationBar: MyBottomBar(),
    );
  }
}
