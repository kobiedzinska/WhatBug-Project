import 'package:flutter/material.dart';
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
  // TODO get data of recently found bugs
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
                    Icon(Icons.lightbulb_outline_rounded, size: 30),
                    Text(
                      'Did you know... ?',
                      style: GoogleFonts.comicNeue(
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
                            style: GoogleFonts.comicNeue(
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
                  style: GoogleFonts.comicNeue(
                    fontSize: 28,
                    fontWeight: FontWeight.bold,
                  ),
                  textAlign: TextAlign.left,
                ),
              ),
              BugCard(
                bugName: 'La kukaracza',
                bugInfo: 'nie ma tak że dobrze albo że niedobrze',
                bugPicture: AssetImage('lib/images/appIcon.png'),
              ),
              BugCard(
                bugName: 'Jelonek rogacz',
                bugInfo: 'jest całkiem średnio',
                bugPicture: AssetImage('lib/images/appIcon.png'),
              ),
              BugCard(
                bugName: 'Cymbałek jakiś',
                bugInfo: 'bążur',
                bugPicture: AssetImage('lib/images/appIcon.png'),
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
