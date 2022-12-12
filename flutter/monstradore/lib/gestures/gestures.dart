import 'package:flutter/cupertino.dart';

class Gestures extends StatefulWidget {
  const Gestures({super.key});

  @override
  State<StatefulWidget> createState() => _GesturesState();
}

class _GesturesState extends State<Gestures> {

  final transformationController = TransformationController();

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        onDoubleTap: handleDoubleTap,
        child: Center(
      child: InteractiveViewer(
          transformationController: transformationController,
        panEnabled: false,
        minScale: 1,
        maxScale: 4,
          child: const Image(image: AssetImage('assets/sample.jpeg'))
      ),
    )
    );
  }

  void handleDoubleTap() {
    if (transformationController.value != Matrix4.identity()) {
      transformationController.value = Matrix4.identity();
    } else {
      transformationController.value = Matrix4.identity()
        ..translate(-150.0, -150.0)
        ..scale(2.0);
    }
  }
}
