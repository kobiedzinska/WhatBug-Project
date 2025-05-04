import 'dart:io';

import 'package:flutter/material.dart';
import 'package:camera/camera.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';
import 'package:gal/gal.dart';

class CameraScreen extends StatefulWidget {
  const CameraScreen({super.key});

  @override
  State<CameraScreen> createState() => _CameraScreenState();
}

class _CameraScreenState extends State<CameraScreen> {
  List<CameraDescription> cameras = [];
  CameraController? cameraController;

  @override
  void initState() {
    super.initState();
    _setupCameraController();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(appBar: const MyAppBar(), body: _buildUI());
  }

  Widget _buildUI() {
    if (cameraController == null ||
        cameraController?.value.isInitialized == false) {
      return const Center(child: CircularProgressIndicator());
    }
    return SafeArea(
      child: SizedBox.expand(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            SizedBox(
              height: MediaQuery.sizeOf(context).height * 0.7,
              width: MediaQuery.sizeOf(context).width,
              child: CameraPreview(cameraController!),
            ),
            IconButton(
              onPressed: () async {
                XFile picture = await cameraController!.takePicture();
                Gal.putImage(picture.path);
                await Navigator.of(context).push(
                  MaterialPageRoute(
                    builder:
                        (context) =>
                            DisplayPictureScreen(imagePath: picture.path),
                  ),
                );
              },
              iconSize: 60,
              icon: const Icon(Icons.camera),
              color: Theme.of(context).colorScheme.primary,
            ),
          ],
        ),
      ),
    );
  }

  Future<void> _setupCameraController() async {
    // Local variables are inherently not visible outside the declaring library, so a leading underscore indicating private adds no value.
    List<CameraDescription> _cameras = await availableCameras();
    if (_cameras.isNotEmpty) {
      setState(() {
        cameras = _cameras;
        cameraController = CameraController(
          cameras.first,
          ResolutionPreset.ultraHigh,
        );
      });
      cameraController?.initialize().then((_) {
        setState(() {});
      });
    }
  }
}

class DisplayPictureScreen extends StatelessWidget {
  final String imagePath;

  const DisplayPictureScreen({super.key, required this.imagePath});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: Image.file(File(imagePath)),
    );
  }
}