import 'package:flutter/material.dart';
import 'package:frontend_flutter/utilities/navigation.dart';

class CameraButton extends StatelessWidget {
  const CameraButton({super.key});

  @override
  Widget build(BuildContext context) {
    return FloatingActionButton.large(
      onPressed: () => openCameraScreen(context),
      shape: CircleBorder(),
      child: Icon(Icons.camera, size: 80.0),
    );
  }
}
