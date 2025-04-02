import 'package:flutter/material.dart';
import 'package:camera/camera.dart';
import 'package:frontend_flutter/utilities/my_app_bar.dart';

class CameraScreen extends StatefulWidget {
  const CameraScreen({super.key});

  @override
  State<CameraScreen> createState() => _CameraScreenState();
}

class _CameraScreenState extends State<CameraScreen> {
  List<CameraDescription> cameras = [];
  CameraController? cameraController;

  void savePhoto() {}

  @override
  void initState() {
    super.initState();
    _setupCameraController();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MyAppBar(),
      body: _buildUI(),
    );
  }

  Widget _buildUI() {
    if (cameraController == null ||
        cameraController?.value.isInitialized == false) {
      return const Center(
        child: CircularProgressIndicator(),
      );
    }
    return SafeArea(
        child: SizedBox.expand(
            child: Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        SizedBox(
            height: MediaQuery.sizeOf(context).height * 0.8,
            width: MediaQuery.sizeOf(context).width,
            child: CameraPreview(
              cameraController!,
            )),
        IconButton(
          onPressed: () async {
            // XFile picture = await cameraController!.takePicture();
            // Gal.putImage(picture.path);
          },
          iconSize: 60,
          icon: const Icon(Icons.camera),
          color: Theme.of(context).colorScheme.primary,
        )
      ],
    )));
  }

  Future<void> _setupCameraController() async {
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
