import 'package:flutter/material.dart';
import 'package:frontend_flutter/utilities/navigation.dart';

class MyBottomBar extends StatelessWidget implements PreferredSizeWidget {
  const MyBottomBar({super.key});

  @override
  Widget build(BuildContext context) {
    return BottomAppBar(
      elevation: 5,
      shadowColor: Theme.of(context).colorScheme.outline,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Expanded(
            child: IconButton(
              onPressed: () {
                goToHomePage(context);
              },
              icon: const Icon(Icons.home, size: 30.0),
            ),
          ),
          Expanded(
            child: IconButton(
              onPressed: () {
                goToBugsScreen(context);
              },
              icon: const Icon(Icons.bug_report, size: 30.0),
            ),
          ),
          Expanded(
            child: IconButton(
              onPressed: () {
                goToSettingsScreen(context);
              },
              icon: const Icon(Icons.settings, size: 30.0),
            ),
          ),
        ],
      ),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(100);
}
