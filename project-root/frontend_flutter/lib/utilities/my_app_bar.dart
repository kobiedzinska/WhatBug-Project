import 'package:flutter/material.dart';

class MyAppBar extends StatelessWidget implements PreferredSizeWidget{
  const MyAppBar({super.key});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      title: const Text("Insektorium"),
      centerTitle: true,
      elevation: 5,
      shadowColor: Theme.of(context).colorScheme.outline,
    );
  }
  
  @override
  Size get preferredSize => const Size.fromHeight(60);
}
