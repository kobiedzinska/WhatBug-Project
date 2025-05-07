import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class MyAppBar extends StatelessWidget implements PreferredSizeWidget{
  const MyAppBar({super.key});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      title: Text("Insektorium", style: GoogleFonts.kablammo(fontSize: 30)),
      centerTitle: true,
      elevation: 5,
      shadowColor: Theme.of(context).colorScheme.outline,
    );
  }
  
  @override
  Size get preferredSize => const Size.fromHeight(70);
}
