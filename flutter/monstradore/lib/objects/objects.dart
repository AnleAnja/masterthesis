import 'package:flutter/material.dart';
import 'package:flutter_cube/flutter_cube.dart';

class Objects extends StatelessWidget {
  const Objects({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Cube(
          onSceneCreated: (Scene scene) {
            scene.world.add(Object(fileName: 'assets/obj/sampleobject.obj'));
          },
        ),
      ),
    );
  }
}
