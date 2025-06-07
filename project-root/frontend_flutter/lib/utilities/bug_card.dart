import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class BugCard extends StatelessWidget {
  const BugCard({
    super.key,
    required this.bugName,
    required this.bugInfo,
    required this.bugPicture,
  });
  final String bugName;
  final String bugInfo;
  final AssetImage bugPicture;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        child: SizedBox(
          width: MediaQuery.of(context).size.width * 0.90,
          child: Padding(
            padding: const EdgeInsets.all(10.0),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Image(
                  image: bugPicture,
                  width: MediaQuery.of(context).size.width * 0.25,
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: SizedBox(
                    width: MediaQuery.of(context).size.width * 0.55,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          bugName,
                          style: GoogleFonts.atma(
                            fontSize: 28,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        SizedBox(
                          child: Text(
                            bugInfo,
                            style: GoogleFonts.atma(
                              fontSize: 20,
                              fontWeight: FontWeight.normal,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
