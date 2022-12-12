import 'package:flutter/material.dart';
import 'package:sensors_plus/sensors_plus.dart';

class Acceleration extends StatefulWidget{
  const Acceleration({super.key});

  @override
  State<StatefulWidget> createState() => _AccelerationState();
}

class _AccelerationState extends State<Acceleration> {

  double x = 0, y = 0, z = 0;

  @override
  void initState() {
    gyroscopeEvents.listen((GyroscopeEvent event) {
      x = event.x;
      y = event.y;
      z = event.z;
      setState(() { });
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final textTheme = theme.textTheme;

    return  Scaffold(
      body: Column(
              children:[
                Text("Beschleunigung", style: textTheme.headline6),
                Text("Roll: $x"),
                Text("Pitch: $y"),
                Text("Yaw: $z")
              ]
          )
    );
  }
}